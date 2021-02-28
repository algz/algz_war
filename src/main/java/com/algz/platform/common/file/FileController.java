/**
 * 
 */
package com.algz.platform.common.file;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algz.platform.common.file.pathencode.APathCode;
import com.algz.platform.common.file.pathencode.APathCodeRepository;

/**
 * @author algz
 *
 */
@RestController
@RequestMapping("/common/file")
public class FileController {

	@Value("${algz.pathcode.filestorePath:}")
	private String filestorePath;
	
	@Autowired
	private APathCodeRepository peReponsitory;
	
	/**
	 * 如果在参数前添加 @ RequestParam ,则请求的参数列表中此参数不能为空,否则找不到此方法; 如果不设置,此参数能为空,也能找到此方法. @
	 * PostMapping 只接收Post方式请求
	 * 
	 * @param extra
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload") 
	public String fileUpload(@RequestParam("file") MultipartFile file, String extra) {
		String path = "d:\\" + "/" + file.getOriginalFilename();
		return processUploadFile(file, extra, path);
	}

	@PostMapping("/uploads")
	public String fileUpload(@RequestParam("file") MultipartFile[] files, String extra) {

		for (MultipartFile file : files) {
			String path = "d:\\" + "/" + file.getOriginalFilename();
			path = new Date().getTime() + ".txt";
			processUploadFile(file, extra, path);
		}
		return "上传成功";
	}

	private String processUploadFile(MultipartFile file, String fileParam, String localFilePath) {
		try {
			System.out.println(file.getName());// 获取表单中文件组件的名字
			System.out.println(file.getOriginalFilename());// 获取上传文件的名字
			System.out.println(file.getSize());// 文件的上传大小
			System.out.println("fileParam:" + fileParam);
			// 根据路径+时间戳+文件后缀名来创建文件
//			File localFile = new File(path, new Date().getTime() + ".txt");
			// 如果是传入服务器 file.getInputStream();用输入输出流来读取
			// 储存为本地文件
			file.transferTo(new File(localFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return localFile.getAbsolutePath();
		return file.getOriginalFilename();
	}

	/**
	 * 下载文件
	 * 
	 * @ GetMapping 只接收Get方式请求.
	 * http://localhost:8080/algz/common/file/down?pathcode=整机参数导入模板.xlsx
	 * @param remoteFileName 远程服务器存储的文件名 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@GetMapping("/down")
	public void downLoad(String pathcode, HttpServletResponse response) throws FileNotFoundException, IOException {
		if(pathcode!=null) {
			Optional<APathCode> op=peReponsitory.findById(pathcode);
			if(op.isPresent()) {
				APathCode pc=op.get();
				String downFilePath=filestorePath+pc.getFilePath();
				File f=new File(downFilePath);
				// JDK 1.7后 可以在try中自动关闭流文件
				// inputStream 输入 读，OutputStream输出.
				try (InputStream inputStream = new FileInputStream(f);
						OutputStream outputStream = response.getOutputStream();) {
					// 设置响应类型
					response.setContentType("application/x-download");
					// Content-disposition是 MIME 协议的扩展，MIME 协议指示 MIME 用户代理如何显示附加的文件。
					//当 Internet Explorer 接收到头时，它会激活文件下载对话框，它的文件名框自动填充了头中指定的文件名。

					String downFilename =  URLEncoder.encode(f.getName(),"UTF-8");//必须编码为UTF-8,不然则是乱码。
					response.addHeader("Content-Disposition", "attachment;filename=" + downFilename);
					IOUtils.copy(inputStream, outputStream);
					outputStream.flush();
				}
				return ;
			}
		}
		
		response.getOutputStream().print("not down file!");
	}
	
	//////////以下暂没测试/////////////
	
	/**
     * 上传大文件(区块)
     *
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(value = "/uploadchunk")
    public Map<String, Object> upload(
            HttpServletRequest request, @RequestParam(value = "data",required = false) MultipartFile multipartFile) throws IllegalStateException, IOException, Exception {

        String action = request.getParameter("action");
        String uuid = request.getParameter("uuid");
        String fileName = request.getParameter("name");
        String size = request.getParameter("size");//总大小
        int total = Integer.valueOf(request.getParameter("total"));//总片数
        int index = Integer.valueOf(request.getParameter("index"));//当前是第几片
        String fileMd5 = request.getParameter("filemd5"); //整个文件的md5
        String date = request.getParameter("date"); //文件第一个分片上传的日期(如:20170122)
        String md5 = request.getParameter("md5"); //分片的md5

        //生成上传文件的路径信息，按天生成
        String savePath = "";//Constant.FILE_PATH + File.separator + date;
        String saveDirectory ="";// PathUtil.getAppRootPath(request) + savePath + File.separator + uuid;
        //验证路径是否存在，不存在则创建目录
        File path = new File(saveDirectory);
        if (!path.exists()) {
            path.mkdirs();
        }
        //文件分片位置
        File file = new File(saveDirectory, uuid + "_" + index);

        //根据action不同执行不同操作. check:校验分片是否上传过; upload:直接上传分片
        Map<String, Object> map = null;
        if("check".equals(action)){
            String md5Str = FileMd5Util.getFileMD5(file);
            if (md5Str != null && md5Str.length() == 31) {
                System.out.println("check length =" + md5.length() + " md5Str length" + md5Str.length() + "   " + md5 + " " + md5Str);
                md5Str = "0" + md5Str;
            }
            if (md5Str != null && md5Str.equals(md5)) {
                //分片已上传过
                map = new HashMap<>();
                map.put("flag", "2");
                map.put("fileId", uuid);
                map.put("status", true);
                return map;
            } else {
                //分片未上传
                map = new HashMap<>();
                map.put("flag", "1");
                map.put("fileId", uuid);
                map.put("status", true);
                return map;
            }
        }else if("upload".equals(action)){
            //分片上传过程中出错,有残余时需删除分块后,重新上传
            if (file.exists()) {
                file.delete();
            }
            multipartFile.transferTo(new File(saveDirectory, uuid + "_" + index));
        }

//        if (path.isDirectory()) {
//            File[] fileArray = path.listFiles();
//            if (fileArray != null) {
//                if (fileArray.length == total) {
//                    //分块全部上传完毕,合并
//                    String suffix = NameUtil.getExtensionName(fileName);
//
//                    File newFile = new File(PathUtil.getAppRootPath(request) + savePath, uuid + "." + suffix);
//                    FileOutputStream outputStream = new FileOutputStream(newFile, true);//文件追加写入
//                    byte[] byt = new byte[10 * 1024 * 1024];
//                    int len;
//
//                    FileInputStream temp = null;//分片文件
//                    for (int i = 0; i < total; i++) {
//                        int j = i + 1;
//                        temp = new FileInputStream(new File(saveDirectory, uuid + "_" + j));
//                        while ((len = temp.read(byt)) != -1) {
//                            System.out.println("-----" + len);
//                            outputStream.write(byt, 0, len);
//                        }
//                    }
//                    //关闭流
//                    temp.close();
//                    outputStream.close();
//                    //修改FileRes记录为上传成功
//                    Example example = new Example(FileRes.class);
//                    Example.Criteria criteria = example.createCriteria();
//                    criteria.andEqualTo("md5",fileMd5);
//                    FileRes fileRes = new FileRes();
//                    fileRes.setStatus(Constant.ONE);
//                    fileResService.updateByExampleSelective(fileRes,example);
//                }else if(index == 1){
//                    //文件第一个分片上传时记录到数据库
//                    FileRes fileRes = new FileRes();
//                    String name = NameUtil.getFileNameNoEx(fileName);
//                    if (name.length() > 50) {
//                        name = name.substring(0, 50);
//                    }
//                    fileRes.setName(name);
//                    fileRes.setSuffix(NameUtil.getExtensionName(fileName));
//                    fileRes.setUuid(uuid);
//                    fileRes.setPath(savePath + File.separator + uuid + "." + fileRes.getSuffix());
//                    fileRes.setSize(Integer.parseInt(size));
//                    fileRes.setMd5(fileMd5);
//                    fileRes.setStatus(Constant.ZERO);
//                    fileRes.setCreateTime(new Date());
//                    this.fileResService.insert(fileRes);
//                }
//            }
//        }

        map = new HashMap<>();
        map.put("flag", "3");
        map.put("fileId", uuid);
        map.put("status", true);
        return map;
    }

    /**
     * 上传大文件前校验
     *
     * @param request
     * @return
     * @throws IOException
     */
//    @RequestMapping(value = "/isUploadchunk")
//    public Map<String, Object> isUpload(HttpServletRequest request) throws Exception {
//
//        String md5 = request.getParameter("md5");
//
//        Example example = new Example(FileRes.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("md5", md5);
//        List<FileRes> list = fileResService.selectByExample(example);
//
//        Map<String, Object> map = null;
//        if (list == null || list.size() == 0) {
//            //没有上传过文件
//            String uuid = UUID.randomUUID().toString();
//            map = new HashMap<>();
//            map.put("flag", "1");
//            map.put("fileId", uuid);
//            map.put("date", new JDateTime().toString("YYYYMMDD"));
//            map.put("status", true);
//        } else {
//            FileRes fileRes = list.get(0);
//            //求文件上传日期
//            SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMDD");
//            Date date=new Date();
//            String strDate=sdf.format(date);
//            if(fileRes.getStatus()==0){
//                //文件上传部分
//                map = new HashMap<>();
//                map.put("flag", "2");
//                map.put("fileId", fileRes.getUuid());
//                map.put("date",strDate);
//                map.put("status", true);
//            }else if(fileRes.getStatus()==1){
//                //文件上传成功
//                map = new HashMap<>();
//                map.put("flag", "3");
//                map.put("fileId", fileRes.getUuid());
//                map.put("date",strDate);
//                map.put("status", true);
//            }
//
//        }
//        return map;
//    }
	
    /**
     * 下载文件使用断点续传功能
     * @param name
     * @param request
     * @param response
     * @throws FileNotFoundException
     */
    @RequestMapping("/download/{name}")
    public void getDownload1(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        // Get your file stream from wherever.
//        logger.info("name="+name);
        String fullPath = ResourceUtils.getURL("classpath:").getPath() + "static/ludashisetup.exe";
//        logger.info("下载路径:"+fullPath);
        File downloadFile = new File(fullPath);
 
        ServletContext context = request.getServletContext();
        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
 
        // set content attributes for the response
        response.setContentType(mimeType);
        // response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        // 解析断点续传相关信息
        response.setHeader("Accept-Ranges", "bytes");
        long downloadSize = downloadFile.length();
        long fromPos = 0, toPos = 0;
        if (request.getHeader("Range") == null) {
            response.setHeader("Content-Length", downloadSize + "");
        } else {
            // 若客户端传来Range，说明之前下载了一部分，设置206状态(SC_PARTIAL_CONTENT)
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            String range = request.getHeader("Range");
            String bytes = range.replaceAll("bytes=", "");
            String[] ary = bytes.split("-");
            fromPos = Long.parseLong(ary[0]);
            if (ary.length == 2) {
                toPos = Long.parseLong(ary[1]);
            }
            int size;
            if (toPos > fromPos) {
                size = (int) (toPos - fromPos);
            } else {
                size = (int) (downloadSize - fromPos);
            }
            response.setHeader("Content-Length", size + "");
            downloadSize = size;
        }
        // Copy the stream to the response's output stream.
        RandomAccessFile in = null;
        OutputStream out = null;
        try {
            in = new RandomAccessFile(downloadFile, "rw");
            // 设置下载起始位置
            if (fromPos > 0) {
                in.seek(fromPos);
            }
            // 缓冲区大小
            int bufLen = (int) (downloadSize < 2048 ? downloadSize : 2048);
            byte[] buffer = new byte[bufLen];
            int num;
            int count = 0; // 当前写到客户端的大小
            out = response.getOutputStream();
            while ((num = in.read(buffer)) != -1) {
                out.write(buffer, 0, num);
                count += num;
                //处理最后一段，计算不满缓冲区的大小
                if (downloadSize - count < bufLen) {
                    bufLen = (int) (downloadSize-count);
                    if(bufLen==0){
                        break;
                    }
                    buffer = new byte[bufLen];
                }
            }
            response.flushBuffer();
        } catch (IOException e) {
//            logger.info("数据被暂停或中断。");
//            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
//                    logger.info("数据被暂停或中断。");
//                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
//                    logger.info("数据被暂停或中断。");
//                    e.printStackTrace();
                }
            }
        }
    }
    
}

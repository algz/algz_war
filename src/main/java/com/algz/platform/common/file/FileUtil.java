package com.algz.platform.common.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	/**
	 * 删除文件夹及其子文件。
	 * java 没有提供删除整个文件夹（含子文件夹）的功能。
	 * @param dir
	 * @return
	 */
	public static boolean deleteDirectory(File dir) {
		if(dir.isDirectory()) {
			File[] subFiles=dir.listFiles();
			for(int i=0;i<subFiles.length;i++) {
				boolean success=deleteDirectory(subFiles[i]);
				if(!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}
	
	/**
	 * 本地（上传）文件存储到服务器
	 * @param destServerFile 服务器文件
	 * @param sourceFile 上传文件
	 * @return
	 */
	public static String addStorageServerFile(File destServerFile,MultipartFile sourceFile) {
//		File dest=new File(serverPath+ file.getOriginalFilename());
		System.out.print("文件参数名称:"+sourceFile.getName());// 获取表单中文件组件的名字
		System.out.print(",文件名称:"+sourceFile.getOriginalFilename());// 获取上传文件的名字
		System.out.print(",文件的上传大小:"+sourceFile.getSize()+" Byte");// 文件的上传大小
		try {
			FileUtil.createDirectory(destServerFile.getParentFile());
			if(destServerFile.exists()) {
				//文件同名重命名
				String filename=FileUtil.getFileNameNoExt(destServerFile.getName())+"_"+new Date().getTime();
				String extname=FileUtil.getExtensionName(destServerFile.getName());
				String newfilepath=destServerFile.getParent()+"/"+filename+"."+extname;
				destServerFile=new File(newfilepath);
			}
			sourceFile.transferTo(destServerFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		return destServerFile.getAbsolutePath();
	}
	

    /**
     * 创建目录
     * @param folder
     * @return
     */
    public static boolean createDirectory(File folder) {
    	if (!folder.exists() && !folder.isDirectory()) {
    	    folder.mkdirs();
    	    System.out.println("创建文件夹");
    	} else {
    	    System.out.println("文件夹已存在");
    	}
    	return true;
    }
	
	/**
     * Java文件操作 获取文件扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename.toLowerCase();
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     */
    public static String getFileNameNoExt(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename.toLowerCase();
    }


    
    public static void main(String[] args) {
        String str = "AAAbb.jpg";
        System.out.println(getExtensionName(str).toLowerCase());
        System.out.println(getFileNameNoExt(str).toUpperCase());
    }
    
    /**
    *
    * @param request
    * @return 返回结果类似于 “F:\workSpace\bookQr\src\main\webapp\”
    */
   public static String  getAppRootPath(HttpServletRequest request){
       //ServletActionContext.getServletContext().getRealPath("/")+"upload";
       return request.getSession().getServletContext().getRealPath("/");
   }

   /**
    *自定义文件保存路径
    * @param request
    */
//   public static String  getCustomRootPath(HttpServletRequest request){
//       String path = "";
//       Properties prop = new Properties();
//       InputStream in = MailController.class.getResourceAsStream("/config/jdbc.properties");
//       try {
//           prop.load(in);
//           path = prop.getProperty("FILE_PATH").trim();
//
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
//       return path;
//   }

   /**
    *
    * @param request
    * @return http://www.qh.com:8080/projectName
    */
   public static String  getHttpURL(HttpServletRequest request) {
       StringBuffer buff = new StringBuffer();
       buff.append("http://");
       buff.append(request.getServerName());
       buff.append(":");
       buff.append(request.getServerPort());
       buff.append(request.getContextPath());
       return buff.toString();
   }
}

package com.algz.platform.common.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class FileUtil {
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
    public static String getFileNameNoEx(String filename) {
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
        System.out.println(getFileNameNoEx(str).toUpperCase());
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

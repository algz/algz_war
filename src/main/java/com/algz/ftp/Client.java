package com.algz.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class Client {

    public static void Upload(String ip,String username,String password,String localFile,String remoteDir,String remoteFileName) {
    	Upload(ip,username,password,localFile,remoteDir,remoteFileName,"GBK");
    }

	 /**
     * FTP上传单个文件测试
     * @param ip 192.168.14.117
     * @param username admin
     * @param password 123
     * @param localFile c:\1.pic
     * @param remoteDir /admin/pic
     * @param fileName 3.gif
     * @param encode GBK
     */
    public static void Upload(String ip,String username,String password,String localFile,String remoteDir,String remoteFileName,String encode) {
        FTPClient ftpClient = new FTPClient();
        FileInputStream fis = null;

        try {
            ftpClient.connect(ip);
            ftpClient.login(username, password);

            fis = new FileInputStream(new File(localFile));
            //设置上传目录
            ftpClient.changeWorkingDirectory(remoteDir);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("GBK");
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.storeFile(remoteFileName, fis);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }

    /**
     * FTP下载单个文件测试
     * @param ip 192.168.14.117
     * @param username admin
     * @param password 123
     * @param localFile c:\1.pic
     * @param remoteFilePath /admin/pic/3.gif
     * @param encode GBK
     */
    public static void Download(String ip,String username,String password,String localFile,String remoteFilePath){
        FTPClient ftpClient = new FTPClient();
        FileOutputStream localfos = null;

        try {
            ftpClient.connect(ip);
            ftpClient.login(username, password);

            localfos = new FileOutputStream(localFile);

            ftpClient.setBufferSize(1024);
            //设置文件类型（二进制）
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.retrieveFile(remoteFilePath, localfos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("FTP客户端出错！", e);
        } finally {
            IOUtils.closeQuietly(localfos);
            try {
                ftpClient.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }
}

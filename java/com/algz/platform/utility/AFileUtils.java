package com.algz.platform.utility;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

public class AFileUtils {

	/**
	 * 创建文件目录
	 * 文件的目录不存在，则自动创建文件目录
	 * @param filePath 文件路径(含文件名称)。 eg: x:\excel\1.xlsx
	 * @return 
	 */
	public static boolean mkdirs(String filePath) {
		File f=new File(filePath);
		return f.getParentFile().mkdirs();//如果目录存在，则自动创建目录。
	}
	
	/**
	 * 获取文件名称，包含扩展名
	 * @param filePath
	 * @return
	 */
	public static String getFileFullName(String filePath) {
		String fileName=filePath.replace("/", "\\");
		 return fileName.substring(fileName.lastIndexOf("\\")+1);
	}
	
	/**
	 * 获取没有扩展名的文件名
	 * @param filePath
	 * @return
	 */
	public static String getFileNameWithNotExtName(String filePath) {
		String f=getFileFullName(filePath);
		return f.substring(0, f.lastIndexOf("."));
	}

	/**
	 * 获取扩展名
	 * @param filePath
	 * @return
	 */
	public static String getExtName(String filePath) {
		String f=getFileFullName(filePath);
		return f.substring(f.lastIndexOf(".") + 1, f.length());
	}
	
	/**
	 * 
	 * String filepath = "D:\file";//D盘下的file文件夹的目录
		File file = new File(filepath);//File类型可以是文件也可以是文件夹
		File[] fileList = file.listFiles();//将该目录下的所有文件放置在一个File类型的数组中
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                //System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            //System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取ClassPath 路径。
     * 如：D:\Source\java\eclipse-workspace\algz_war\target\classes\
     * src/main/java、src/main/webapp和src/main/resources 默认都对应这一个目录。
     * @return
     */
    public static String getClassPath() {
    	String url=null;
    	try {
			url = ResourceUtils.getURL("classpath:").getPath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	return url;
    }
    
//    public static void main(String[] args) {
////  // 删除单个文件
////  String file = "c:/test/test.txt";
////  DeleteFileUtil.deleteFile(file);
////  System.out.println();
//        // 删除一个目录
//        String dir = "D:/home/web/upload/upload/files";
//        DeleteFileUtil.deleteDirectory(dir);
////  System.out.println();
////  // 删除文件
////  dir = "c:/test/test0";
////  DeleteFileUtil.delete(dir);
//
//    }
}

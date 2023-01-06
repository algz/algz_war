package com.algz.office;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	/**
	 * 
	 * @param filePath "workbook.xls"
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void CreateWorkbookOfXLS(String filePath) throws FileNotFoundException, IOException {
		try(Workbook wb = new HSSFWorkbook()){
			try  (OutputStream fileOut = new FileOutputStream(filePath)) {
			    wb.write(fileOut);
			}
		}

	}
	
	public static void CreateWorkbookOfXLSL(String filePath) throws FileNotFoundException, IOException {
		try(Workbook wb = new XSSFWorkbook()){
			try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
			    wb.write(fileOut);
			}
		}
	}
	
	public static void CreateSheet() throws FileNotFoundException, IOException {
		try(Workbook wb = new HSSFWorkbook()) // or new XSSFWorkbook();
		{
			Sheet sheet1 = wb.createSheet("new sheet");
			Sheet sheet2 = wb.createSheet("second sheet");
			// Note that sheet name is Excel must not exceed 31 characters
			// and must not contain any of the any of the following characters:
			// 0x0000
			// 0x0003
			// colon (:)
			// backslash (\)
			// asterisk (*)
			// question mark (?)
			// forward slash (/)
			// opening square bracket ([)
			// closing square bracket (])
			// You can use org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String nameProposal)}
			// for a safe way to create valid names, this utility replaces invalid characters with a space (' ')
			String safeName = WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]"); // returns " O'Brien's sales   "
			Sheet sheet3 = wb.createSheet(safeName);
			try (OutputStream fileOut = new FileOutputStream("workbook.xls")) {
			    wb.write(fileOut);
			}
		}
	}
	
	/**
	 * 打开工作簿
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public static void OpenWorkbook() throws EncryptedDocumentException, IOException {
		// 1.1 Use a file
		Workbook wb = WorkbookFactory.create(new File("MyExcel.xls"));
		// 1.2 使用 InputStream，需要更多内存,因为它必须缓冲整个文件。
//		Workbook wb = WorkbookFactory.create(new FileInputStream("MyExcel.xlsx"));
		
		// 2. HSSFWorkbook, File,通常应该通过PIOFSFileSystem或 OPCPackage来完全控制生命周期（包括完成后关闭文件）
		POIFSFileSystem fs = new POIFSFileSystem(new File("file.xls"));
		HSSFWorkbook wb1 = new HSSFWorkbook(fs.getRoot(), true);
		fs.close();
	}
}

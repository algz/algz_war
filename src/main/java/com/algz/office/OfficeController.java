package com.algz.office;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo")
@RestController
public class OfficeController {
	
	@Value("${algz.pathcode.filestorePath:}")
	private String filestorePath;
	
	@RequestMapping("/excel")
	private String GetExcel() {
		try {
			String filePath=filestorePath+"/1.xls";
//			ExcelUtils.CreateWorkbookOfXLS(filestorePath+"/1.xls");
			try(Workbook wb = new HSSFWorkbook()){ //工作簿
				Sheet sheet = wb.createSheet("new sheet"); //创建工作表
				
				
				CreateHeader(sheet);
				List<ExcelEntity> entityList=new ArrayList();
				ExcelEntity ee=new ExcelEntity();
				ee.setNum("1row");
				ee.setCategory("燃油");
				ee.setSemantic("主油箱油量");
				ee.setUserInput("5000以上");
				ee.setIndicators("0到1000kg");
				entityList.add(ee);
				ee=new ExcelEntity();
				ee.setNum("2row");
				ee.setCategory("燃油");
				ee.setSemantic("主油箱油量");
				ee.setUserInput("6000以上");
				ee.setIndicators("0到1000kg");
				entityList.add(ee);
				ee=new ExcelEntity();
				ee.setNum("3row");
				ee.setCategory("燃油1");
				ee.setSemantic("主油箱油量");
				ee.setUserInput("6000以上");
				ee.setIndicators("0到1000kg");
				entityList.add(ee);
				CreateContent(sheet,entityList);
				
				MergedRegionForColumn(sheet);
				

				try  (OutputStream fileOut = new FileOutputStream(filePath)) {
				    wb.write(fileOut);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 创建表头。
	 * @param sheet1
	 */
	private void CreateHeader(Sheet sheet1) {
		Row row1=sheet1.createRow(0); //创建第几行。基于0开始
		String[] headers= {"序号","功能分类","功能语义具体条目","用户输入","对应指标","引导判据","仿真部件名称","仿真子模型","子模型路径"};
		for(int i=0; i<headers.length;i++) {
			Cell cell=row1.createCell(i);
			cell.setCellValue(headers[i]);
		}
	}
	
	/**
	 * 创建内容
	 * @param sheet
	 * @param entityList
	 */
	private void CreateContent(Sheet sheet,List<ExcelEntity> entityList) {
		for(int i=0;i<entityList.size();i++) {
			ExcelEntity entity=entityList.get(i);
			Row row=sheet.createRow(i+1);
			row.createCell(0).setCellValue(entity.getNum());
			row.createCell(1).setCellValue(entity.getCategory());
			row.createCell(2).setCellValue(entity.getSemantic());
			row.createCell(3).setCellValue(entity.getUserInput());
			row.createCell(4).setCellValue(entity.getIndicators());
			row.createCell(5).setCellValue(entity.getRule());
			row.createCell(6).setCellValue(entity.getPartName());
			row.createCell(7).setCellValue(entity.getSubModelName());
			row.createCell(8).setCellValue(entity.getSubModelPath());
		}
	}


	/**
	 * 按列的顺序，逐行合并单元格，
	 * @param sheet
	 */
	private void MergedRegionForColumn(Sheet sheet) {
		Row row=sheet.getRow(0);
		// 决定要处理的行
		int rowStart = Math.min(15, sheet.getFirstRowNum());
		int rowEnd = Math.min(1400, sheet.getLastRowNum());
		// 决定要处理的列
		int colStart = row.getFirstCellNum();
		int colEnd = row.getLastCellNum();//1;//
		

		for (int colNum = colStart; colNum < colEnd; colNum++) {
			String preCellStr = "";
			int mSRow=rowStart;
			
			for (int rowNum = rowStart; rowNum <= rowEnd; rowNum++) {
				row=sheet.getRow(rowNum);
				Cell curCell = row.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
				if(rowNum==rowStart) {
					preCellStr = curCell.getStringCellValue();
				}
				
				if (curCell == null) {
					// The spreadsheet is empty in this cell
				} else {
					// 当前行号!=开始行号，并且上一个单元格值与当前单元格值是一样
					if(mSRow!=rowNum&&preCellStr.equals(curCell.getStringCellValue())){
						//1.最后一行，合并单元格；2.或者不是最后一行，但下一个单元格值不一样，则合并单元格。
						if(rowNum==rowEnd||(rowNum!=rowEnd&&!sheet.getRow(rowNum+1).getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue().equals(curCell.getStringCellValue()))) {
							sheet.addMergedRegion(new CellRangeAddress(
									mSRow, //first row (0-based)
									rowNum, //last row  (0-based)
									colNum, //first column (0-based)
									colNum  //last column  (0-based)
							));
						}
					}else{
						//上一个单元格值与当前单元格值不一样，则开始行序号设为当前行。
						mSRow=rowNum;
					}
					preCellStr = curCell.getStringCellValue();
				}
			}
		}
	}

}

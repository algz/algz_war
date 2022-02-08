package com.cf611.requirmentJudge.office;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.util.ArrayUtils;

import com.algz.platform.common.file.pathencode.APathCodeService;


@Service
public class OfficeServiceImp implements OfficeService{

	@Value("${algz.pathcode.filestorePath:}")
	private String filestorePath;


	/**
	 * @param fileName 文件名称(可以含路径)。eg: exlcel/123.xlsx 或 123.xls
	 */
	public String GetExcelFilePath(String fileName,List<ExcelEntity> entityList) {
		
		String filePath=filestorePath+(fileName.substring(0,1).equals("/")?"":File.separator)+fileName;
		
//		ExcelUtils.CreateWorkbookOfXLS(filestorePath+"/1.xls");
		try {
			File f=new File(filePath);
			f.getParentFile().mkdirs();//如果目录存在，则自动创建目录。
//			f.isDirectory()
			String extName=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
			try(Workbook wb =(extName.equals("xlsx")?new XSSFWorkbook(): new HSSFWorkbook())){ //工作簿
				Sheet sheet = wb.createSheet("new sheet"); //创建工作表
				
				String[] headers= {"序号","功能分类","功能语义具体条目","用户输入","对应指标","引导判据",
						"仿真部件名称","仿真子模型","子模型路径","换行"};
				CreateHeader(sheet,headers);
				
//				List<ExcelEntity> entityList=new ArrayList();
//				ExcelEntity ee=new ExcelEntity();
//				ee.setNum("1row");
//				ee.setCategory("燃油");
//				ee.setSemantic("主油箱油量");
//				ee.setUserInput("5000以上");
//				ee.setIndicators("0到1000kg");
//				entityList.add(ee);

				CreateContent(sheet,entityList);
				
				MergedRegionForColumn(sheet,new int[]{1,2,3});
				

				try  (OutputStream fileOut = new FileOutputStream(filePath)) {
				    wb.write(fileOut);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return filePath;
	}
	
	/**
	 * 创建表头。
	 * @param sheet1
	 * @param headers 列标题名称
	 */
	public void CreateHeader(Sheet sheet1,String[] headers) {
		Row row1=sheet1.createRow(0); //创建第几行。基于0开始
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
	public void CreateContent(Sheet sheet,List<ExcelEntity> entityList) {
		for(int i=0;i<entityList.size();i++) {
			ExcelEntity entity=entityList.get(i);
			Row row=sheet.createRow(i+1);
			row.createCell(0).setCellValue(entity.getSerialNo());
			row.createCell(1).setCellValue(entity.getKindName());
			row.createCell(2).setCellValue(entity.getSemanticsName());
			row.createCell(3).setCellValue(entity.getUserIndicatorName());
			row.createCell(4).setCellValue(entity.getIndicatorName());
			row.createCell(5).setCellValue(entity.getRegulationName());
			row.createCell(6).setCellValue(entity.getComponentName());
			row.createCell(7).setCellValue(entity.getSubModelName());
			row.createCell(8).setCellValue(entity.getSubModelPath());
			row.createCell(9).setCellValue(entity.getNewLine());
		}
	}


	/**
	 * 按列的顺序，逐行合并单元格，
	 * @param sheet
	 * @param mergedCols 如果为null，则全部列合并；如果mergedCols.length>0,则合并指定的列。从0列开始。
	 */
	public void MergedRegionForColumn(Sheet sheet,int[] mergedCols) {
		Row row=sheet.getRow(0);
		// 决定要处理的行
		int rowStart = Math.min(15, sheet.getFirstRowNum());
		int rowEnd = Math.min(1400, sheet.getLastRowNum());
		// 决定要处理的列
		int colStart = row.getFirstCellNum();
		int colEnd = row.getLastCellNum();//1;//
		//int[] 数组转换成 List类型
        List<Integer> list = Arrays.stream(mergedCols).boxed().collect(Collectors.toList());
		for (int colNum = colStart; colNum < colEnd; colNum++) {
//			ArrayList<Integer> obj =new ArrayList<Integer>(Arrays.asList(1,2,3));
			if(mergedCols!=null&&mergedCols.length!=0&&!list.contains(colNum)) {
						continue;
			}
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

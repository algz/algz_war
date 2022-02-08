package com.cf611.requirmentJudge.office;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

public interface OfficeService {
	/**
	 * @see OfficeServiceImp#GetExcelFilePath(String, List)
	 * @param fileName
	 * @param entityList
	 * @return
	 */
	public String GetExcelFilePath(String fileName,List<ExcelEntity> entityList);
	
	public void CreateHeader(Sheet sheet1,String[] headers);
	
	public void CreateContent(Sheet sheet,List<ExcelEntity> entityList);
	
	public void MergedRegionForColumn(Sheet sheet,int[] mergedCols);
}

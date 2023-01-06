package com.cf611.doc;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.spire.doc.*;
import com.spire.doc.documents.*;
import com.spire.doc.fields.TextRange;

/**
 * 
 * @author algz
 *
 */
@Service
public class ReportServiceImp implements ReportService {

	@Override
	public void exportFile(OutputStream out) {
		// TODO Auto-generated method stub
        //Create word document.
        Document document =new Document();

        //Add a new section.
        Section section = document.addSection();

        //Add a new paragraph.
        Paragraph paragraph = section.addParagraph();

        //Append Text.
        paragraph.appendText("文件报告模板");

//      //数据
//        String[] header = {"姓名", "性别", "部门", "工号"};
//        String[][] data =
//                {
//                        new String[]{"Winny", "女", "综合", "0109"},
//                        new String[]{"Lois", "女", "综合", "0111"},
//                        new String[]{"Jois", "男", "技术", "0110"},
//                        new String[]{"Moon", "女", "销售", "0112"},
//                        new String[]{"Vinit", "女", "后勤", "0113"},
//                };
//
//        //添加表格
//        Table table = section.addTable(true);
//        //设置表格的行数和列数
//        table.resetCells(data.length + 1, header.length);
//
//        //设置第一行作为表格的表头并添加数据
//        TableRow row = table.getRows().get(0);
//        row.isHeader(true);
//        row.setHeight(20);
//        row.setHeightType(TableRowHeightType.Exactly);
//        row.getRowFormat().setBackColor(Color.gray);
//        for (int i = 0; i < header.length; i++) {
//            row.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
//            Paragraph p = row.getCells().get(i).addParagraph();
//            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
//            TextRange range1 = p.appendText(header[i]);
//            range1.getCharacterFormat().setFontName("Arial");
//            range1.getCharacterFormat().setFontSize(12f);
//            range1.getCharacterFormat().setBold(true);
//        }
//
//        //添加数据到剩余行
//        for (int r = 0; r < data.length; r++) {
//            TableRow dataRow = table.getRows().get(r + 1);
//            dataRow.setHeight(25);
//            dataRow.setHeightType(TableRowHeightType.Exactly);
//            dataRow.getRowFormat().setBackColor(Color.white);
//            for (int c = 0; c < data[r].length; c++) {
//                dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
//                TextRange range2 = dataRow.getCells().get(c).addParagraph().appendText(data[r][c]);
//                range2.getCharacterFormat().setFontName("Arial");
//                range2.getCharacterFormat().setFontSize(10f);
//            }
//        }
//
//        //设置单元格背景颜色
//        for (int j = 1; j < table.getRows().getCount(); j++) {
//            if (j % 2 == 0) {
//                TableRow row2 = table.getRows().get(j);
//                for (int f = 0; f < row2.getCells().getCount(); f++) {
//                    row2.getCells().get(f).getCellFormat().setBackColor(new Color(173, 216, 230));
//                }
//            }
//        }

        
        //Save to file.
        String url="";
		try {
			url = ResourceUtils.getURL("classpath:").getPath();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        com.spire.license.LicenseProvider.setLicenseFileFullPath(url + "spire/license.elic.xml");
//        document.saveToFile("c:/output/helloWorld.docx", FileFormat.Docx);
        document.saveToFile(out, FileFormat.Docx);
	}
}

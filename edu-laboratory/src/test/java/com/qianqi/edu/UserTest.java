package com.qianqi.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class UserTest {

	public static void main(String[] args) {
		try {
			md5();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void md5() throws IOException, EncryptedDocumentException, InvalidFormatException
	{
		File f = new File("/Users/guang/Downloads/a.xlsx");
		FileInputStream fileInputStream = new FileInputStream(f);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();
        List<String> colNames = new ArrayList<>();
        for (Row row : sheet) {
//        		if(sheet.getRow(0) == row)
//        			continue;
            for (Cell cell : row) {
//                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                //单元格名称
//                System.out.print(cellRef.formatAsString());
//                System.out.print(" - ");

                //通过获取单元格值并应用任何数据格式（Date，0.00，1.23e9，$ 1.23等），获取单元格中显示的文本
                String text = formatter.formatCellValue(cell);
                
                if(row.getRowNum() == 0)
                {
                		colNames.add(text);
                }
                else
                {
                		System.out.print(text+"    "+colNames.get(cell.getColumnIndex()));
                }
                 //获取值并自己格式化
//                switch (cell.getCellType()) {
//                    case Cell.CELL_TYPE_STRING:// 字符串型
//                        System.out.print(cell.getRichStringCellValue().getString() + "   ");
//                        break;
//                    case Cell.CELL_TYPE_NUMERIC:// 数值型
//                        if (DateUtil.isCellDateFormatted(cell)) { // 如果是date类型则 ，获取该cell的date值
//                            System.out.print(cell.getDateCellValue()+ "   ");
//                        } else {// 纯数字
//                            System.out.print(cell.getNumericCellValue()+ "   ");
//                        }
//                        break;
//                    case Cell.CELL_TYPE_BOOLEAN:// 布尔
//                        System.out.print(cell.getBooleanCellValue()+ "   ");
//                        break;
//                    case Cell.CELL_TYPE_FORMULA:// 公式型
//                        System.out.print(cell.getCellFormula()+ "   ");
//                        break;
//                    case Cell.CELL_TYPE_BLANK:// 空值
//                        System.out.print("   ");
//                        break;
//                    case Cell.CELL_TYPE_ERROR: // 故障
//                        System.out.print("   ");
//                        break;
//                    default:
//                        System.out.print("   ");
//                }
            }
            System.out.println();
        }
	}
}

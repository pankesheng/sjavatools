package com.ks.utils.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SExcelReader {

	/**
	 * 返回excel数据
	 * List中的Map<Integer, String>表示每个单元格的数据  key：单元格序号，从0开始  value：单元格数据
	 * @param is 
	 * @param startRow 从第几行开始读数据，下标从0开始
	 * @param endCell 读取表格的最后一列列号 序号从0开始 ，可为空，为空时表示自动，但此时如果第一列数据为空时 整行不读
	 * @throws IOException
	 */
	public static List<Map<Integer, String>> readXls(InputStream is,int startRow,Integer endCell) throws IOException {
		List<Map<Integer, String>> datas = new ArrayList<Map<Integer, String>>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = startRow; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					break;
				}
				Map<Integer, String> rowData = new HashMap<Integer, String>();
				// 循环列Cell
				int cellTotal = hssfRow.getLastCellNum();
				if(endCell!=null){
					cellTotal = endCell;
				}
				for (int cellNum = 0; cellNum <= cellTotal; cellNum++) {
					HSSFCell hssfCell = hssfRow.getCell(cellNum);
					if (hssfCell == null && endCell == null) {
						continue;
					}
					String value = "";
					if (hssfCell!=null) value = getValue(hssfCell);
					rowData.put(cellNum, value);
				}
				if(rowData.isEmpty()){
					break;
				}
				datas.add(rowData);
			}
		}
		is.close();
		return datas;
	}

	/**
	 * 返回excel数据
	 * List中的Map<Integer, String>表示每个单元格的数据  key：单元格序号，从0开始  value：单元格数据
	 * @param fileName
	 * @param startRow 从第几行开始读数据，下标从0开始
	 * @param endCell 读取表格的最后一列列号 序号从0开始 ，可为空，为空时表示自动，但此时如果第一列数据为空时 整行不读
	 * @throws IOException
	 */
	public static List<Map<Integer, String>> readXlsx(String fileName,int startRow,Integer endCell) throws IOException {
		List<Map<Integer, String>> datas = new ArrayList<Map<Integer, String>>();
		XSSFWorkbook xssfWorkbook = null;
		FileInputStream fis = null;
		fis = new FileInputStream(fileName);
		xssfWorkbook = new XSSFWorkbook(fis);
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = startRow; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					break;
				}
				Map<Integer, String> rowData = new HashMap<Integer, String>();
				int cellTotal = xssfRow.getLastCellNum();
				if(endCell!=null){
					cellTotal = endCell;
				}
				// 循环列Cell
				for (int cellNum = 0; cellNum <= cellTotal; cellNum++) {
					XSSFCell xssfCell = xssfRow.getCell(cellNum);
					if (xssfCell == null && endCell == null) {
						continue;
					}
					String value = "";
					if(xssfCell != null) value = getValue(xssfCell);
					rowData.put(cellNum, value);
				}
				if(rowData.isEmpty()){
					break;
				}
				datas.add(rowData);
			}
		}
		fis.close();
		return datas;
	}
	
	public static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {// 处理日期格式、时间格式  
                SimpleDateFormat sdf = null;  
                if (hssfCell.getCellStyle().getDataFormat() == HSSFDataFormat  
                        .getBuiltinFormat("h:mm")) {  
                    sdf = new SimpleDateFormat("HH:mm");  
                } else {// 日期  
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                }  
                Date date = hssfCell.getDateCellValue();  
                return sdf.format(date);
            } else if (hssfCell.getCellStyle().getDataFormat() == 58) {  
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                double value = hssfCell.getNumericCellValue();  
                Date date = org.apache.poi.ss.usermodel.DateUtil  
                        .getJavaDate(value);  
               return sdf.format(date);  
            } else {  
                double value = hssfCell.getNumericCellValue();  
                CellStyle style = hssfCell.getCellStyle();  
                DecimalFormat format = new DecimalFormat();  
                String temp = style.getDataFormatString();  
                // 单元格设置成常规  
                if (temp.equals("General")) {  
                    format.applyPattern("#");  
                }  
                return format.format(value);  
            }
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	
	public static String getValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {// 处理日期格式、时间格式  
                SimpleDateFormat sdf = null;  
                if (xssfCell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {  
                    sdf = new SimpleDateFormat("HH:mm");  
                } else {// 日期  
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                }  
                Date date = xssfCell.getDateCellValue();  
                return sdf.format(date);
            } else if (xssfCell.getCellStyle().getDataFormat() == 58) {  
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
                double value = xssfCell.getNumericCellValue();  
                Date date = org.apache.poi.ss.usermodel.DateUtil  
                        .getJavaDate(value);  
               return sdf.format(date);  
            } else {  
                double value = xssfCell.getNumericCellValue();  
                CellStyle style = xssfCell.getCellStyle();  
                DecimalFormat format = new DecimalFormat();  
                String temp = style.getDataFormatString();  
                // 单元格设置成常规  
                if (temp.equals("General")) {  
                    format.applyPattern("#");  
                }  
                return format.format(value);  
            }  
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}
	
}
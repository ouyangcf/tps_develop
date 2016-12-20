package com.unlcn.ils.tps.excel;

import java.io.IOException; 
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelModel {
	private static Logger logger=Logger.getLogger(Logger.class);
	public OutputStream dataPossess(Map<String, String> condition,OutputStream os){
		logger.info("正在调用dataPossess方法");
		HSSFWorkbook workbook=new HSSFWorkbook();
		logger.info("已实例化HSSFWorkbook");
		HSSFSheet sheet = workbook.createSheet();
		logger.info("已创sheet");
		logger.info("获取数据");
		DataListModel dataModel=new DataListModel();
		List<Map<String, String>> dataList=dataModel.getCheckDataList(condition);
		logger.info("准备打印表格");
		if (dataList==null) {
			logger.info("数据list集合为空");
		}
		if (dataList!=null) {
			logger.info("数据list集合不为空");
		}		
		//打印表头
		for(int i=0;i<dataList.size();i++){
			Map<String, String> map=dataList.get(i);
			HSSFRow row = sheet.createRow(i);
			int j=0;
			for (Map.Entry<String, String> entry : map.entrySet()) {
				//将信息一个个打印出来
				HSSFCell cell = row.createCell(j);    
				if (entry.getValue()!=null) {
					cell.setCellValue(new HSSFRichTextString(entry.getValue()));
				}
				else{
					cell.setCellValue(new HSSFRichTextString(""));
				}
				j++;
			}
			logger.info("我在dataPossess方法的第一个for循环中");
		}
		try {
			workbook.write(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("调用完成dataPossess方法");
		return os;
	}
}

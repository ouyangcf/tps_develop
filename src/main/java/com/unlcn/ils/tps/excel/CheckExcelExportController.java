package com.unlcn.ils.tps.excel;
 
import java.io.IOException; 
import java.io.OutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class CheckExcelExportController extends HttpServlet { 
	private static Logger logger=Logger.getLogger(Logger.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		GregorianCalendar calendar=new GregorianCalendar();
		String currentDate=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
		response.setHeader("Content-disposition", "attachment; filename="+new String(("导出ex").getBytes("GB2312"),"iso8859-1")+currentDate+".xls");// 设定输出文件头   
		response.setContentType("application/msexcel");// 定义输出类型 
		OutputStream os=response.getOutputStream();
		Map<String, String> condition=new HashMap<String, String>();
		if (request.getParameter("shipperName")!=null) {
			condition.put("shipperName", request.getParameter("shipperName"));
			logger.info("从前端获取的shipperName是："+request.getParameter("shipperName"));
		}
		if(request.getParameter("startMonth")!=null){
			condition.put("startMonth", request.getParameter("startMonth"));
			logger.info("从前端获取的startMonth是："+request.getParameter("startMonth"));
		}
		if(request.getParameter("endMonth")!=null){
			condition.put("endMonth", request.getParameter("endMonth"));
			logger.info("从前端获取的endMonth是："+request.getParameter("endMonth"));
		}
		CheckExcelModel excelModel=new CheckExcelModel();
		os=excelModel.dataPossess(condition, os);
		logger.info("我在这里");
		os.flush();
        os.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("正在调用ExcelController");
		doGet(request, response);
	}
}

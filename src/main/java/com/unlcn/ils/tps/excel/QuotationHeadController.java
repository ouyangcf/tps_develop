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
public class QuotationHeadController extends HttpServlet { 
	private static Logger logger=Logger.getLogger(Logger.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		GregorianCalendar calendar=new GregorianCalendar();
		String currentDate=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
		response.setHeader("Content-disposition", "attachment; filename="+new String(("导出ex").getBytes("GB2312"),"iso8859-1")+currentDate+".xls");// 设定输出文件头 
		response.setContentType("application/msexcel");// 定义输出类型 
		OutputStream os=response.getOutputStream();
		Map<String, String> condition=new HashMap<String, String>();
		if (request.getParameter("shipperid")!=null) {
			condition.put("shipperid", request.getParameter("shipperid"));
			logger.info("从前端获取的shipperid是："+request.getParameter("shipperid"));
		}
		if(request.getParameter("payperiod")!=null){
			condition.put("payperiod", request.getParameter("payperiod"));
			logger.info("从前端获取的payperiod是："+request.getParameter("payperiod"));
		}
		if(request.getParameter("urgency")!=null){
			condition.put("urgency", request.getParameter("urgency"));
			logger.info("从前端获取的urgency是："+request.getParameter("urgency"));
		}
		if(request.getParameter("quotationno")!=null){
			condition.put("quotationno", request.getParameter("quotationno"));
			logger.info("从前端获取的quotationno是："+request.getParameter("quotationno"));
		}
		QuotationExcelModel excelModel=new QuotationExcelModel();
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

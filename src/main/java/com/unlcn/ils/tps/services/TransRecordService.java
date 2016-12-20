package com.unlcn.ils.tps.services;
import java.util.ArrayList; 
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
import com.unlcn.ils.erp.dto.FleetDto;

import com.unlcn.ils.tps.ininterface.TransportInterface;
public class TransRecordService implements ArrayContentProvider{
	private static  final Logger logger = Logger.getLogger(Logger .class);
	/**
	 * 
	 * @param shippername
	 * @return
	 * @Description:历史承运信息自定义数据源
	 */
	
		
	@SuppressWarnings("unused")
	@Override
	public Page<?> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		logger.info("正在初始化自定义逻辑数据源");
		logger.info("获取上下文过滤条件");
		Map<String, Object> conditions = context.getCondition();
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		List<Rule> rules = condition.getRules();
		String lineid=new String();
		for ( int i=0;i<rules.size();i++)
		{//得到查询条件
			Rule rule = rules.get(i);
			String field = rule.getField();
			String data = (String) rule.getData();
			String op = rule.getOp();
			if ( field.equals("lineid")){
				logger.info("获取的lineid为"+data);
				lineid=data;
			}
		}
		TransportInterface transportInterface=new TransportInterface();
		FleetDto fleetDto=new FleetDto();
		fleetDto.setVcfleetname(lineid);
		 GregorianCalendar startCalendar=new GregorianCalendar();
		 Integer startyear=startCalendar.get(Calendar.YEAR);
		 Integer startmonth=startCalendar.get(Calendar.MONTH)+1;
		 Integer startday=startCalendar.get(Calendar.DAY_OF_MONTH);
		 GregorianCalendar endCalendar=new GregorianCalendar();
		 endCalendar.add(Calendar.MONTH, -12); 
		 Integer endyear=endCalendar.get(Calendar.YEAR);
		 Integer endmonth=endCalendar.get(Calendar.MONTH)+1;
		 Integer endday=endCalendar.get(Calendar.DAY_OF_MONTH);		 
		 String startDate=startyear+"-"+startmonth+"-"+startday;
		 String endDate=endyear+"-"+endmonth+"-"+endday;
		fleetDto.setStartDate(startDate);
		fleetDto.setEndDate(endDate);
		List<Map<String, String>>	returnlist=transportInterface.getTransByidFromCrm(fleetDto);
		
		int pagesize = context.getPageable().getPageSize();
		int pageindex = context.getPageable().getPageIndex();
		Page<Map<String,String>> resultdata = new Page<Map<String,String>>();
		resultdata.setPageIndex(pageindex);
		resultdata.setPageSize(pagesize);
		resultdata.setTotal(returnlist.size());
		List<Map<String, String>>  resultList = getDataByPage(pageindex, pagesize, returnlist);
		resultdata.setContents(resultList);
		return resultdata;
	}
	private List<Map<String, String>> getDataByPage(int page,int size,List<Map<String, String>> datas){
		List<Map<String, String>> returnList=new ArrayList<Map<String, String>>();
		int min=page*size<=datas.size()?page*size:datas.size();
		for(int i=(page-1)*size;i<min;i++){
			returnList.add(datas.get(i));
		}
		return returnList;
	}
}


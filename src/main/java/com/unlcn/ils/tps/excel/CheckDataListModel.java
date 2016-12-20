package com.unlcn.ils.tps.excel; 

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.unlcn.ils.tps.E_checkhead;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;

public class CheckDataListModel {    
	private static Logger logger=Logger.getLogger(Logger.class);
	@SuppressWarnings("static-access")
	public List<Map<String, String>> getCheckDataList(Map<String, String> condition){
		Dao<E_checkhead> dao=new DaoFactory().create(E_checkhead.class);
		String custom_select="com.unlcn.ils.tps.E_checkheadMapper.custom_select";
		E_checkhead condition_checkhead=new E_checkhead();
		if (condition.get("shipperName")!=null) {
			condition_checkhead.setShipperLineid(condition.get("shipperName"));
		} 
		if (condition.get("startMonth")!=null) {
			Timestamp startTime=Timestamp.valueOf(condition.get("startMonth"));
			Timestamp endTime=Timestamp.valueOf(condition.get("endMonth"));
			logger.info("起始日期为："+startTime.toString());
			logger.info("结束日期为："+endTime.toString());
			condition_checkhead.setCreateTime(startTime);
			condition_checkhead.setActiveTime(endTime);
		}
		List<E_checkhead> checkheads=dao.getSession().selectList(custom_select, condition_checkhead);
		//List<E_checkhead> checkheads=dao.selectAll();
		logger.info("获取的考核单表头数量为: "+checkheads.size());
		List<Map<String, String>> returnList=new ArrayList<Map<String, String>>();
		returnList.add(getTitleMap4CheckHead());
		for(int i=0;i<checkheads.size();i++){
			Map<String, String> map=new HashMap<String, String>();
			E_checkhead checkHead=checkheads.get(i);
			map.put("LineID", checkHead.getLineid());
			map.put("Shipper_LineID", checkHead.getShipperLineid());
			map.put("Shipper_Name", checkHead.getShipperName());
			logger.info("获取到分供方名称为："+checkHead.getShipperName());
			if (checkHead.getCheckStatus()!=null) {
				if (checkHead.getCheckStatus()==0) {
					map.put("CheckStatus", "未审核");
				}
				if (checkHead.getCheckStatus()==1) {
					map.put("CheckStatus", "提交审核未处理");
				}
				if (checkHead.getCheckStatus()==2) {
					map.put("CheckStatus", "审核通过");
				}		
				if (checkHead.getCheckStatus()==3) {
					map.put("CheckStatus","审核不通过");
				}	
			}
			if (checkHead.getCollectStatus()!=null) {
				if (checkHead.getCollectStatus()==0) {
					map.put("CollectStatus", "未审核");
				}
				if (checkHead.getCollectStatus()==1) {
					map.put("CollectStatus", "提交审核未处理");
				}	
			}
			map.put("CheckMonth", checkHead.getCountmonth());
			if(checkHead.getFactSubmoney()!=null)
			map.put("factSubmoney", checkHead.getFactSubmoney().toString());
			else{
				map.put("factSubmoney", "");
			}
			if(checkHead.getSubMoney()!=null)
			map.put("subMoney", checkHead.getSubMoney().toString());
			else{
				map.put("subMoney", "");
			}
			if(checkHead.getFronzenMoney()!=null)
			map.put("fronzenMoney", checkHead.getFronzenMoney().toString());
			else{
				map.put("fronzenMoney", "");
			}
			if(checkHead.getDeductMoney()!=null)
			map.put("deductMoney", checkHead.getDeductMoney().toString());
			else{
				map.put("deductMoney", "");
			}
			if(checkHead.getCheckMoney()!=null)
			map.put("checkMoney", checkHead.getCheckMoney().toString());
			else{
				map.put("checkMoney", "");
			}
			if(checkHead.getStandardSubmoney()!=null)
			map.put("standardSubmoney", checkHead.getStandardSubmoney().toString());
			else{
				map.put("standardSubmoney", "");
			}
			if(checkHead.getStandardSubvalue()!=null)
			map.put("standardSubvalue", checkHead.getStandardSubvalue().toString());
			else{
				map.put("standardSubvalue", "");
			}			
			if(checkHead.getStandardValue()!=null)
				map.put("standardValue", checkHead.getStandardValue().toString());
			else{
				map.put("standardValue", "");
			}			
			if(checkHead.getArgueStatus()!=null){
				if (checkHead.getArgueStatus()==0) {
					map.put("argueStatus", "未申诉");	
				}
				if (checkHead.getArgueStatus()==1) {
					map.put("argueStatus", "部分申诉");	
				}
				if (checkHead.getArgueStatus()==2) {
					map.put("argueStatus", "已申诉");	
				}
			}
			else{
				map.put("argueStatus", "");
			}
			if(checkHead.getArgueTime()!=null){
				map.put("argueTime", checkHead.getArgueTime().toString());	
			}
			else{
				map.put("argueTime", "");
			}
			map.put("Create_UserName", checkHead.getCreateUsername());
			map.put("Create_Time", checkHead.getCreateTime().toString());
			returnList.add(map);
		}
		return returnList;
	}
	
	private Map<String, String> getTitleMap4CheckHead(){
		Map<String, String> map=new HashMap<String, String>();
		map.put("LineID", "流水号");
		map.put("Shipper_LineID", "分供方流水号");
		map.put("Shipper_Name", "分供方名称");
		map.put("CheckStatus", "考核单状态");
		map.put("CollectStatus", "执行汇总状态");
		map.put("CheckMonth", "考核年月");
		map.put("factSubmoney", "扣款金额");
		map.put("subMoney", "汇总减免金额");
		map.put("fronzenMoney", "汇总冻结金额");
		map.put("deductMoney", "汇总扣款金额");
		map.put("checkMoney", "汇总考核金额");
		map.put("standardSubmoney", "标准考核金额");
		map.put("standardSubvalue", "本月扣分");		
		map.put("standardValue", "标准扣分");		
		map.put("argueStatus", "申诉状态");	
		map.put("argueTime", "申诉截止时间");	
		map.put("Create_UserName", "创建人");
		map.put("Create_Time", "创建时间");
		return map;
	}
}

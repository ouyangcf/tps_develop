package com.unlcn.ils.tps.excel;

import java.sql.Timestamp; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.unlcn.ils.tps.E_checkdocument;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;

public class DataListModel {
	private static Logger logger=Logger.getLogger(Logger.class);
	@SuppressWarnings("static-access")
	public List<Map<String, String>> getCheckDataList(Map<String, String> condition){
		logger.info("正在调用getCheckDataList方法");
		Dao<E_checkdocument> dao=new DaoFactory().create(E_checkdocument.class);
		String custom_select="com.unlcn.ils.tps.E_checkdocumentMapper.custom_select";
		E_checkdocument condition_checkdocument=new E_checkdocument();
		if (condition.get("shipperName")!=null) {
			condition_checkdocument.setShipperLineid(condition.get("shipperName"));
			logger.info("获取的shipperName为"+condition.get("shipperName"));
		}
		if (condition.get("item")!=null) {
			Integer itemLineid=Integer.parseInt(condition.get("item"));
			condition_checkdocument.setItemLineid(itemLineid);
			logger.info("获取的item为"+condition.get("item"));
		}
		if (condition.get("operate")!=null) {
			Integer operateLineid=Integer.parseInt(condition.get("operate"));
			condition_checkdocument.setOperateLineid(operateLineid);
			logger.info("获取的operate为"+condition.get("operate"));
		}
		if (condition.get("startMonth")!=null) {
			Timestamp startTime=Timestamp.valueOf(condition.get("startMonth"));
			Timestamp endTime=Timestamp.valueOf(condition.get("endMonth"));
			condition_checkdocument.setCreateTime(startTime);
			condition_checkdocument.setActiveTime(endTime);
			logger.info("获取的startMonth为"+condition.get("startMonth"));
			logger.info("获取的endMonth为"+condition.get("endMonth"));
		}
		if (condition.get("flag")!=null) {
			Integer flag=Integer.parseInt(condition.get("flag"));
			condition_checkdocument.setFlag(flag);
			logger.info("获取的flag为"+condition.get("flag"));
		}
		List<E_checkdocument> checkdocuments=dao.getSession().selectList(custom_select, condition_checkdocument);
		List<Map<String, String>> returnList=new ArrayList<Map<String, String>>();
		returnList.add(getTitleMap4CheckDoc());
		for(int i=0;i<checkdocuments.size();i++){
			logger.info("第"+i+"个循环开始");
			Map<String, String> map=new HashMap<String, String>();
			E_checkdocument checkdocument=checkdocuments.get(i);
			if (checkdocument.getLineid()!=null) {
				map.put("LineID", checkdocument.getLineid());
			}
			else{
				map.put("LineID", "");
			}
			if (checkdocument.getShipperLineid()!=null) {
				map.put("Shipper_LineID", checkdocument.getShipperLineid());
			}
			else{
				map.put("Shipper_LineID", "");
			}
			if(checkdocument.getShipperName()!=null){
				map.put("Shipper_Name", checkdocument.getShipperName());
			}
			else{
				map.put("Shipper_Name","");
			}
			if (checkdocument.getItemLineid()!=null) {
				map.put("Item_LineID", checkdocument.getItemLineid().toString());
			}
			else{
				map.put("Item_LineID", "");
			}
			if (checkdocument.getItemName()!=null) {
				map.put("Item_Name", checkdocument.getItemName());
			}
			else{
				map.put("Item_Name", "");
			}
			if (checkdocument.getOperateLineid()!=null) {
				map.put("Operate_LineID", checkdocument.getOperateLineid().toString());
			}
			else{
				map.put("Operate_LineID", "");
			}
			if (checkdocument.getOperateName()!=null) {
				map.put("Operate_Name", checkdocument.getOperateName());
			}
			else{
				map.put("Operate_Name", "");
			}
			if (checkdocument.getStartDate()!=null) {
				map.put("Start_date", checkdocument.getStartDate().toString());
			}
			else{
				map.put("Start_date", "");
			}
			if (checkdocument.getCheckNumber()!=null) {
				map.put("Check_Number", checkdocument.getCheckNumber().toString());
			}
			else {
				map.put("Check_Number", "");
			}
			if (checkdocument.getCheckMoney()!=null) {
				map.put("Check_Money", checkdocument.getCheckMoney().toString());
			}
			else {
				map.put("Check_Money", "");
			}
			if (checkdocument.getCheckMoneyStandard()!=null) {
				map.put("Check_Money_Standard", checkdocument.getCheckMoneyStandard().toString());
			}
			else {
				map.put("Check_Money_Standard", "");
			}
			if (checkdocument.getCheckScore()!=null) {
				map.put("Check_Score", checkdocument.getCheckScore().toString());
			}
			else {
				map.put("Check_Score", "");
			}
			if (checkdocument.getCheckScoreStandard()!=null) {
				map.put("Check_Score_Standard", checkdocument.getCheckScoreStandard().toString());
			}
			else {
				map.put("Check_Score_Standard", "");
			}
			if (checkdocument.getCheckMemo()!=null) {
				map.put("Check_Memo", checkdocument.getCheckMemo());
			}
			else {
				map.put("Check_Memo", "");
			}
			if (checkdocument.getCreateUsername()!=null) {
				map.put("Create_UserName", checkdocument.getCreateUsername());
			}
			else {
				map.put("Create_UserName", "");
			}
			if (checkdocument.getCreateTime()!=null) {
				map.put("Create_Time", checkdocument.getCreateTime().toString());
			}
			else {
				map.put("Create_Time", "");
			}
			Integer flag=checkdocument.getFlag();
			if (flag==0) {
				map.put("Flag", "未生成");
			}
			else{
				map.put("Flag", "已生成");
			}
			returnList.add(map);
			logger.info("第"+i+"个循环结束");
		}
		return returnList;
	}
	
	
	private Map<String, String> getTitleMap4CheckDoc(){
		Map<String, String> map=new HashMap<String, String>();
		map.put("LineID", "主键");
		map.put("Shipper_LineID", "分供方id");
		map.put("Shipper_Name", "分供方名称");
		map.put("Item_LineID", "考核项目序号");
		map.put("Item_Name", "考核项目名称");
		map.put("Operate_LineID", "考核操作规范类型序号");
		map.put("Operate_Name", "考核类型名称");
		map.put("Start_date", "项目发生日期");
		map.put("Check_Number", "发生数量");
		map.put("Check_Money", "考核金额");
		map.put("Check_Money_Standard", "标准考核金额");
		map.put("Check_Score", "扣分");
		map.put("Check_Score_Standard", "标准扣分");
		map.put("Check_Memo", "考核说明");
		map.put("Create_UserName", "创建人");
		map.put("Create_Time", "创建时间");
		map.put("Flag", "生成考核单标识");
		return map;
	}
}

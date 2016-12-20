package com.unlcn.ils.tps.services;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.unlcn.ils.tps.E_argue;
import com.unlcn.ils.tps.E_checkhead;
import com.unlcn.ils.tps.E_join_company;
import com.unlcn.ils.tps.E_join_driver;
import com.unlcn.ils.tps.E_levelchange;
import com.unlcn.ils.tps.E_revise;

public class DealServices {
	@SuppressWarnings({ "static-access", "unused" })
	public List<Map<String, String>> getDeals(){
		Dao<E_levelchange> levelChangeDao=new DaoFactory().create(E_levelchange.class);
		Dao<E_join_company> companyDao=new DaoFactory().create(E_join_company.class);
		Dao<E_join_driver> driverDao=new DaoFactory().create(E_join_driver.class);
		Dao<E_argue> argueDao=new DaoFactory().create(E_argue.class);
		Dao<E_revise> reviseDao=new DaoFactory().create(E_revise.class);
		Dao<E_checkhead> checkHeaDao=new DaoFactory().create(E_checkhead.class);
		E_levelchange conditionLevel=new E_levelchange();
		conditionLevel.setApproveStatus(2);
		E_join_company conditionCom=new E_join_company();
		conditionCom.setCheckFlag(0);
		E_join_driver conditionDriver=new E_join_driver();
		conditionCom.setCheckFlag(0);
		E_argue conditionArgue=new E_argue();
		conditionArgue.setArgueStatus(1);
		E_revise conditionRevise=new E_revise();
		conditionRevise.setReviseStatus(2);
		E_checkhead conditionCheckhead=new E_checkhead();
		conditionCheckhead.setCheckStatus(1);
		List<E_levelchange> levelchanges=levelChangeDao.select(conditionLevel);
		List<E_join_company> companies=companyDao.select(conditionCom);
		List<E_join_driver> drivers=driverDao.select(conditionDriver);
		List<E_argue> argues=argueDao.select(conditionArgue);
		List<E_revise> revises=reviseDao.select(conditionRevise);
		List<E_checkhead> checkheads=checkHeaDao.select(conditionCheckhead);
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		Map<String, String> map0=new HashMap<String, String>();		
		map0.put("form", "/ils/#/template/iframe.jsp?src=/tps/f/levelCheck&title=分级审核");
		map0.put("tips", "您有"+levelchanges.size()+"条等级调整需要处理");
		Map<String, String> map1=new HashMap<String, String>();
		map1.put("form", "/ils/#/template/iframe.jsp?src=/tps/f/auditCompany&title=分供方合作初步认证");
		map1.put("tips", "您有"+companies.size()+"条分供方合作初步认证需要处理");
		Map<String, String> map2=new HashMap<String, String>();
		map2.put("form", "/ils/#/template/iframe.jsp?src=/tps/f/auditDriver&title=分供方加盟初步认证");
		map2.put("tips", "您有"+levelchanges.size()+"条分供方加盟初步认证需要处理");
		Map<String, String> map3=new HashMap<String, String>();
		map3.put("form", "/ils/#/template/iframe.jsp?src=/tps/f/argueDeal&title=申诉处理");
		map3.put("tips", "您有"+argues.size()+"条申诉处理需要处理");
		Map<String, String> map4=new HashMap<String, String>();
		map4.put("form", "/ils/#/template/iframe.jsp?src=/tps/f/reviseDeal&title=整改处理");
		map4.put("tips", "您有"+revises.size()+"条整改处理需要处理");
		Map<String, String> map5=new HashMap<String, String>();
		map5.put("form", "/ils/#/template/iframe.jsp?src=/tps/f/checkMoneyDeal&title=考核资金审批");
		map5.put("tips", "您有"+checkheads.size()+"条考核资金审批需要处理");
		list.add(map0);
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		return list;
	}
}

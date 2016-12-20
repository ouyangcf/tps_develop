package com.unlcn.ils.tps.services;

import java.util.ArrayList; 
import java.util.List;

import com.unlcn.ils.tps.E_transway;  

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Order;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
/**
 * 
 *@Title:
 *@Description:运输方式
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class TransWayService {
	public List<E_transway> getAllData()
	{
		Dao<E_transway> data=DaoFactory.create(E_transway.class);
		E_transway condition=new E_transway();
		condition.setActive(1);
		Sortable sortname= new Sortable();
		List<Order> orders = new ArrayList<Order>();
		Order e = new Order();
		e.setOrderProperty("transName");
		e.setOrderDirection("asc");
		orders.add(e);
		sortname.setOrders(orders);
		List<E_transway> results=data.select(condition, sortname);
		return results;
		//return data.selectAll(sortname);
	}
}

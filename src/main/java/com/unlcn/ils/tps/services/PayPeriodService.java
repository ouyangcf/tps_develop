package com.unlcn.ils.tps.services;

import java.util.ArrayList; 
import java.util.List;

import com.unlcn.ils.tps.E_pay_period;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Order;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
/**
 * 
 *@Title:
 *@Description:
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class PayPeriodService {
	public List<E_pay_period> getAllData()
	{
		Dao<E_pay_period> data=DaoFactory.create(E_pay_period.class);
		E_pay_period condition=new E_pay_period();
		condition.setActive(1);
		Sortable sortname= new Sortable();
		List<Order> orders = new ArrayList<Order>();
		Order e = new Order();
		e.setOrderProperty("payPeriodName");
		e.setOrderDirection("asc");
		orders.add(e);
		sortname.setOrders(orders);
		List<E_pay_period> results=data.select(condition, sortname);
		return results;
		//return data.selectAll(sortname);
	}
}

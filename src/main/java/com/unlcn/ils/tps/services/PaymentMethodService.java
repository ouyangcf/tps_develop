package com.unlcn.ils.tps.services;

import java.util.ArrayList; 
import java.util.List;

import org.apache.log4j.Logger;
import com.unlcn.ils.tps.E_payment_method;


import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Order;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
/**
 * 
 *@Title:
 *@Description:支付方法服务
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class PaymentMethodService {
	private static Logger logger=Logger.getLogger(Logger.class);
	public List<E_payment_method> getAllData()
	{
		Dao<E_payment_method> data=DaoFactory.create(E_payment_method.class);
		E_payment_method condition=new E_payment_method();
		condition.setActive(1);
		Sortable sortname= new Sortable();
		List<Order> orders = new ArrayList<Order>();
		Order e = new Order();
		e.setOrderProperty("paymentName");
		e.setOrderDirection("asc");
		orders.add(e);
		sortname.setOrders(orders);
		List<E_payment_method> results=data.select(condition, sortname);
		for (E_payment_method e_payment_method : results) {
			logger.info("获取的数据的active为"+e_payment_method.getActive());
		}
		return results;
		//return data.selectAll(sortname);
	}
};


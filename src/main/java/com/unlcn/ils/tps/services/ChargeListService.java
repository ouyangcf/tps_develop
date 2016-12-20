package com.unlcn.ils.tps.services; 

import java.util.ArrayList;
import java.util.List;

import com.unlcn.ils.tps.E_charge_list;
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
public class ChargeListService {
	public List<E_charge_list> getAllData()
	{
		Dao<E_charge_list> data=DaoFactory.create(E_charge_list.class);
		E_charge_list condition=new E_charge_list();
		condition.setActive(1);
		Sortable sortname= new Sortable();
		List<Order> orders = new ArrayList<Order>();
		Order e = new Order();
		e.setOrderProperty("chargeName");
		e.setOrderDirection("asc");
		orders.add(e);
		sortname.setOrders(orders);
		List<E_charge_list> results=data.select(condition, sortname);
		return results;
		//return data.selectAll(sortname);
	}
}

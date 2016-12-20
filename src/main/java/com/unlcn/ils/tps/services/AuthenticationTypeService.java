package com.unlcn.ils.tps.services; 

import java.util.ArrayList;
import java.util.List;

import com.unlcn.ils.tps.E_authentication_type;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Order;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
/**
 * 
 *@Title:
 *@Description:二次认证类型服务
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class AuthenticationTypeService {
	/**
	 * 
	 * @return
	 * @Description:获取所有二次认证类型
	 */
	public List<E_authentication_type> getAllData()
	{
		Dao<E_authentication_type> data=DaoFactory.create(E_authentication_type.class);
		
		Sortable sortname= new Sortable();
		List<Order> orders = new ArrayList<Order>();
		Order e = new Order();
		e.setOrderProperty("linename");
		e.setOrderDirection("asc");
		orders.add(e);
		sortname.setOrders(orders);
		return data.selectAll(sortname);
	}
}

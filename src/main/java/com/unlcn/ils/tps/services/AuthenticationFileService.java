package com.unlcn.ils.tps.services;

import java.text.SimpleDateFormat;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_authentication;  
import com.unlcn.ils.tps.ininterface.ShipperInterface;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Order;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/**
 * 
 *@Title:
 *@Description:二次认证附件上传服务  
 *@Author:Administrator
 *@Since:2016-8-19 
 *@Version:1.1.0 
 */
public class AuthenticationFileService  implements ArrayContentProvider{

	//得到认证记录
	@Override
	public Page<?> getElements(ArrayContext context) 
	{
		Map<String, Object> conditions = context.getCondition();
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;		
		
		Sortable sort = context.getSortable();
		
		if (sort == null)
		{	
			List<Order> orders = new ArrayList<Order>();
			Order e = new Order();
			e.setOrderProperty("lineid");
			e.setOrderDirection("desc");
			orders.add(e);
			sort= new Sortable();
			sort.setOrders(orders);
		}
		
		Dao<E_authentication> data=DaoFactory.create(E_authentication.class);		
		Page<E_authentication> edata = data.selectPageByCondition(conditions, 
				condition,context.getPageable(),sort,true);
		
		List<Map<String,String>> listdata=new ArrayList<Map<String,String>>();
		SimpleDateFormat dftime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dfdate = new SimpleDateFormat("yyyy-MM-dd");
		for ( int i=0;i<edata.getContents().size();i++)
		{	
			E_authentication auth = edata.getContents().get(i);
			Map<String,String> map=new HashMap<String,String> ();
			map.put("lineid", auth.getLineid().toString());
			map.put("shipperid", auth.getShippperId().toString());
			Map<String,String> shipper = ShipperInterface.getShipperByID(auth.getShippperId().toString());
			String shippername = shipper.get("shipname");			
			map.put("shippername", shippername);
			map.put("authenticationuser", auth.getAuthenticationUser());
			map.put("authenticationflag", (auth.getAuthenticationFlag()==1?"认证通过":"认证不通过"));
			
			
			map.put("authenticationtime", dftime.format(auth.getAuthenticationTime()));
			
			if ( auth.getBeginDate()==null)
				map.put("beginDate", "");
			else
				map.put("beginDate", dfdate.format(auth.getBeginDate()));
			if ( auth.getBeginDate()==null)
				map.put("endDate", "");
			else
				map.put("endDate", dfdate.format(auth.getEndDate()));
			map.put("authenticationmemo", auth.getAuthenticationMemo());
			map.put("authenticationreason", (auth.getAuthenticationReasonid()==null?"":auth.getAuthenticationReasonid().getLinename()));
			listdata.add(map);			
		}
		int pagesize = context.getPageable().getPageSize();
		int pageindex = context.getPageable().getPageIndex();
		int total = edata.getTotal();
		
		Page<Map<String,String>> resultdata = new Page<Map<String,String>>();
		resultdata.setPageIndex(pageindex);
		resultdata.setPageSize(pagesize);
		resultdata.setTotal(total);
		resultdata.setContents(listdata);
		return resultdata;
	}
}

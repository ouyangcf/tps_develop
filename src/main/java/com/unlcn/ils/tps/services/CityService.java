package com.unlcn.ils.tps.services;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_city;
import com.unlcn.ils.tps.E_province;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Order;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;
/**
 * 
 *@Title:
 *@Description:城市服务
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class CityService implements TreeContentProvider {
	@Override
	public TreeNode[] getElements (TreeContext context)
	{
		context.getConditions();//得到默认带过来的条件
		
		List<TreeNode> result = new ArrayList<TreeNode>();
		if ( context.getPid() == null)
		{//第一次加载
			//得到省份
			ProvinceService oprovince = new ProvinceService();
			List<E_province> provinces = oprovince.getAllData();//排序显示
			for(int i = 0;i<provinces.size();i++)
			{
				E_province province = provinces.get(i);
				DefaultTreeNode node = new DefaultTreeNode("root_0",
						province.getLineid().toString(),province.getProvincename(),true);				
				result.add(node);
			}
		}
		else
		{//非第一级
			String pid = context.getPid();
			List<E_city> citys = getCityByProvinceID(Integer.parseInt(pid));
			for(int i = 0;i<citys.size();i++)
			{
				E_city city = citys.get(i);
				DefaultTreeNode node = new DefaultTreeNode(pid,
						city.getLineid().toString(),city.getCityname(),false);				
				result.add(node);
			}
		}
		return result.toArray(new TreeNode[0]);			
	}
	/**
	 * 
	 * @param provinceid
	 * @return
	 * @Description:根据省份id获取城市
	 */
	public List<E_city> getCityByProvinceID(int provinceid)
	{
		Dao<E_city> data=DaoFactory.create(E_city.class);		
		Sortable sortname= new Sortable();		
		List<Order> orders = new ArrayList<Order>();
		Order e = new Order();
		e.setOrderProperty("cityname");
		e.setOrderDirection("asc");
		orders.add(e);
		sortname.setOrders(orders);
		E_city ccity = new E_city();
		ccity.setProvinceid(provinceid);		
		return data.select(ccity, sortname);
		
	} 
	/**
	 * 
	 * @return
	 * @Description:获取城市id
	 */
	public List<Integer> getCityId(){
		Dao<E_city> data=DaoFactory.create(E_city.class);
		List<E_city> cities=data.selectAll();
		List<Integer> idlists=new ArrayList<Integer>();
		for(int i=0;i<cities.size();i++){
			idlists.add(cities.get(i).getLineid());
		}
		return idlists;
	}
	/**
	 * 
	 * @return
	 * @Description:获取城市名称
	 */
	public List<String> getCityName(){
		Dao<E_city> data=DaoFactory.create(E_city.class);
		List<E_city> cities=data.selectAll();
		List<String> idlists=new ArrayList<String>();
		for(int i=0;i<cities.size();i++){
			idlists.add(cities.get(i).getCityname());
		}
		return idlists;
	}
	
	@SuppressWarnings("unused")
	public Map<String, String> getMapByCity(String id){
		Integer cityid=Integer.parseInt(id);
		new DaoFactory();
		Dao<E_city> dao=DaoFactory.create(E_city.class);
		E_city getCity=dao.selectByID(id);
		Map<String, String> map=new HashMap<String, String>();
		map.put("province", getCity.getProvincename());
		map.put("city", getCity.getCityname());
		return map;
	}
}

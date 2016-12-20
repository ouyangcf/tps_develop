package com.unlcn.ils.tps.services;

import java.util.HashMap; 

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Order;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;

import com.unlcn.ils.tps.E_city;
import com.unlcn.ils.tps.E_province;
/**
 * 
 *@Title:
 *@Description:省份服务
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class ProvinceService implements TreeContentProvider
{
	/**
	 * 
	 * @return
	 * @Description:获取所有数据
	 */
	public List<E_province> getAllData()
	{
		Dao<E_province> data=DaoFactory.create(E_province.class);
		
		Sortable sortname= new Sortable();
		
		List<Order> orders = new ArrayList<Order>();
		Order e = new Order();
		e.setOrderProperty("provincename");
		e.setOrderDirection("asc");
		orders.add(e);
		sortname.setOrders(orders);
		return data.selectAll(sortname);
	}

	@Override
	public TreeNode[] getElements(TreeContext context) {
		// TODO Auto-generated method stub
		context.getConditions();//得到默认带过来的条件
		List<TreeNode> result = new ArrayList<TreeNode>();
		Dao<E_province> provinceDao=DaoFactory.create(E_province.class); 
		List<E_province> provinces=provinceDao.selectAll();
		if ( context.getPid() == null)
		{//第一次加载
			//得到分供方		
			for(int i = 0;i<provinces.size();i++)
			{
				DefaultTreeNode node = new DefaultTreeNode("root_0",
						provinces.get(i).getLineid().toString(),provinces.get(i).getProvincename(),false);				
				result.add(node);
			}
		}
		return result.toArray(new TreeNode[0]);	
	}
	/**
	 * 
	 * @return
	 * @Description:获取省份id
	 */
	public List<Integer> getProvinceId(){
		Dao<E_province> data=DaoFactory.create(E_province.class);
		List<E_province> cities=data.selectAll();
		List<Integer> idlists=new ArrayList<Integer>();
		for(int i=0;i<cities.size();i++){
			idlists.add(cities.get(i).getLineid());
		}
		return idlists;
	}
	/**
	 * 
	 * @return
	 * @Description:获取省份名称
	 */
	public List<String> getProvinceName(){
		Dao<E_province> data=DaoFactory.create(E_province.class);
		List<E_province> cities=data.selectAll();
		List<String> idlists=new ArrayList<String>();
		for(int i=0;i<cities.size();i++){
			idlists.add(cities.get(i).getProvincename());
		}
		return idlists;
	}
	/**
	 * 
	 * @return
	 * @Description:获取省份
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Map> getProvinces(){
		Dao<E_province> provinceDao=DaoFactory.create(E_province.class);
		List<E_province> listProvinces=provinceDao.selectAll();
		List<Map> returnList=new ArrayList<Map>();
		for(int i=0;i<listProvinces.size();i++){
			Map map=new HashMap();
			map.put(listProvinces.get(i).getLineid().toString(),listProvinces.get(i).getProvincename());
			returnList.add(map);
		}
		return returnList;
	}
	/**
	 * 
	 * @return
	 * @Description:获取城市
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> getCities(){
		List<Map>  returnList=new ArrayList<Map>();
		
		Dao<E_city> cityDao=DaoFactory.create(E_city.class);
		Dao<E_province> provinceDao=DaoFactory.create(E_province.class);
		List<E_province> listProvinces=provinceDao.selectAll();
		String selectByProvinceID="com.unlcn.ils.tps.E_cityMapper.selectByProvinceID";
		for(int i=0;i<listProvinces.size();i++){
			Map map=new HashMap();
			List<E_city> cityList=cityDao.getSession().selectList(selectByProvinceID,listProvinces.get(i).getLineid());
			List<Map> tempList=new ArrayList<Map>();
			for(int j=0;j<cityList.size();j++){		
				Map map2=new HashMap();
				map2.put(cityList.get(j).getLineid().toString(), cityList.get(j).getCityname());
				tempList.add(map2);
			}
			map.put("contents", tempList);
			returnList.add(map);
		}
		
		return returnList;
	}
	/**
	 * 
	 * @return
	 * @Description:获取省份id
	 */
	@SuppressWarnings("static-access")
	public List<Integer> getProvincesId(){
		Dao<E_province> provinceDao=new DaoFactory().create(E_province.class);
		List<E_province> listProvinces=provinceDao.selectAll();
		List<Integer> returnList=new ArrayList<Integer>();
		for(int i=0;i<listProvinces.size();i++){
			returnList.add(listProvinces.get(i).getLineid());
		}
		return returnList;
	}
}

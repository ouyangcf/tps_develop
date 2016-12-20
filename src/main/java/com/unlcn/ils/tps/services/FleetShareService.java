package com.unlcn.ils.tps.services;

import com.chinacreator.c2.context.OperationContextHolder; 
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.unlcn.ils.tps.*;
import com.unlcn.ils.tps.common.TpsRuntimeException; 
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import com.unlcn.ils.tps.ininterface.*;
/**
 * 
 *@Title:
 *@Description:份额配置服务 
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class FleetShareService implements ArrayContentProvider
{
	WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	String username =context.getUser().getName();
	/*检查线路数据后，返加正确的实体给下一步骤完成保存
	入参：E_fleetshare实体
	 */
	public boolean AddData(List<String> shipperId,List<String> startCityId,List<String> destProvinceId,List<String> scale,
			List<String> totalqty,List<String> beginDate,List<String> endDate) 
	{
		Dao<E_fleetshare> dao=DaoFactory.create(E_fleetshare.class);
		E_fleetshare fleetshare=new E_fleetshare();
		for(int i=0;i<shipperId.size();i++){
			SqlInterface sqlinterface = new SqlInterface();
			String lineid= sqlinterface.getNextVal("SEQ_TPS_FLEET_SHARE");
			int cityid = Integer.valueOf(startCityId.get(i));
			E_city city = new E_city();
			city.setLineid(cityid);
			Dao<E_city> cdao=DaoFactory.create(E_city.class);
			city = cdao.selectOne(city);
			fleetshare.setStartCity(city.getCityname());
			fleetshare.setStartCityId(cityid);
			fleetshare.setLineid(lineid);	
			fleetshare.setLineno(lineid);
			fleetshare.setShipperId(shipperId.get(i));
			int provinceid = Integer.valueOf(destProvinceId.get(i));
			E_province province = new E_province();
			province.setLineid(provinceid);
			Dao<E_province> daoprovince=DaoFactory.create(E_province.class);
			province = daoprovince.selectOne(province);
			fleetshare.setDestProvince(province.getProvincename());
			fleetshare.setDestProvinceId(provinceid);
			int b=Integer.valueOf(totalqty.get(i));
			fleetshare.setTotalqty(new BigDecimal(b));
			int a=Integer.valueOf(scale.get(i));
			fleetshare.setScale(new BigDecimal(a));
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			fleetshare.setCreateTime(currdate);
			fleetshare.setActive(1);
			fleetshare.setCreateUser(username);
			fleetshare.setBeginDate(Timestamp.valueOf(beginDate.get(i)));
			fleetshare.setEndDate(Timestamp.valueOf(endDate.get(i)));
			dao.insert(fleetshare);
		}
		return true;
		
	}
	public E_fleetshare checkData(E_fleetshare data) 
	{
		
		//检查数据
		if  (data.getStartCityId() == null)
			throw new TpsRuntimeException("1001");
			//throw new C2RuntimeException("错误的起运城市，请检查数据");
		if  (data.getDestProvinceId() == null)
			throw new RuntimeException("错误的目的地省份，请检查数据");
		if  (data.getScale() == null)
			throw new RuntimeException("错误的份额比(%)，请检查数据");
	/*	if  (data.getLineno() == null)
			throw new TpsRuntimeException("1001");
			//throw new RuntimeException("错误的份额编号，请检查数据");
*/		if  (data.getTotalqty() == null)
			throw new RuntimeException("错误的预计发运量，请检查数据");
		if  (data.getBeginDate() == null)
			throw new RuntimeException("错误的有效日期，请检查数据");
		if  (data.getEndDate() == null)
			throw new RuntimeException("错误的失效日期，请检查数据");
		
		//得到城市名称
		int cityid = data.getStartCityId();
		E_city city = new E_city();
		city.setLineid(cityid);
		Dao<E_city> dao=DaoFactory.create(E_city.class);
		city = dao.selectOne(city);
		
		data.setStartCity(city.getCityname());
		if ( data.getLineid() == null)
		{
			SqlInterface sqlinterface = new SqlInterface();
			String lineid= sqlinterface.getNextVal("SEQ_TPS_FLEET_SHARE");
			data.setLineid(lineid);	
			data.setLineno(lineid);
		}
		
		//得到省份
		int provinceid = data.getDestProvinceId();
		E_province province = new E_province();
		province.setLineid(provinceid);
		Dao<E_province> daoprovince=DaoFactory.create(E_province.class);
		province = daoprovince.selectOne(province);

		data.setDestProvince(province.getProvincename());

		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		data.setCreateTime(currdate);
		data.setActive(1);
		data.setCreateUser(username);
		
		//检查比例
		if ( (data.getScale().doubleValue() >100) || (data.getScale().doubleValue()<0))
			
		if ( (data.getScale().doubleValue() >100) || (data.getScale().doubleValue()<0))
		{
			throw new RuntimeException("错误的份额比例，请检查数据");			
		}
		
		//检查有效期
		if ( data.getBeginDate().after(data.getEndDate()) )
		{
			throw new RuntimeException("错误的有效期，请检查数据");
		}
		return data;
	}

	
	/*分页查询override
		入参：context
	 */
	
	@Override
	public Page<E_fleetshare> getElements(ArrayContext context)
	{
		Map<String, Object> conditions = context.getCondition();
		
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;		
		Dao<E_fleetshare> data=DaoFactory.create(E_fleetshare.class);		
		Page<E_fleetshare> sharelist = data.selectPageByCondition(conditions, 
				condition,context.getPageable(),context.getSortable(),true);
		for ( int i=0;i<sharelist.getContents().size();i++)
		{	
			E_fleetshare fleetshare = sharelist.getContents().get(i);
			if ( fleetshare.getShipperId()==null) continue;
			Map<String,String> shipper = null;
			try
			{	
				//shipper = CustomerInterface.getCustomerByID(fleetshare.getShipperId().toString());
				ShipperService shipperServices=new ShipperService();
				shipper=shipperServices.getShipperById(fleetshare.getShipperId().toString());
			}catch(Exception e)
			{	
			}
			if ( shipper == null) continue;
			String shippername = shipper.get("name");
			
			fleetshare.setShipperName(shippername);
		}
		
		return sharelist;
	}
	/*删除线路函数
	入参：postdata,json格式，存储相应的份额id
	 */
	@SuppressWarnings("unused")
	public int delete()
	{
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();		
		String userid = context.getUser().getId();
		Map<String, Object> input = context.getInput();
		String data=(String) input.get("postdata");//Lined列表,用,号分开
		
		Map<String, Object> param=new HashMap<String, Object>();
	    param.put("activememo", "取消");             
	    param.put("activeuser",username);
	    
	    //多 条选 中的数据 
	    String[] aLine = data.split(",");
	    param.put("listlineid",aLine);
	    
	    Dao<E_fleetshare> dao=DaoFactory.create(E_fleetshare.class);
		int count = dao.getSession().update("com.unlcn.ils.tps.E_fleetshareMapper.deleteBatchLogic", param);		
		return count;
	}
	public List<E_fleetshare> getFleetShareByShipper()
	{//根据供方查询分供方份额
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		Map<String, Object> input = context.getInput();
		String shipperId=(String) input.get("shipperid");//分供方序号
		
		
		Dao<E_fleetshare> dao=DaoFactory.create(E_fleetshare.class);
		
		E_fleetshare share = new E_fleetshare();
		share.setShipperId(shipperId);
		List<E_fleetshare> listdata = dao.select(share, null);
		return listdata;
	}
}

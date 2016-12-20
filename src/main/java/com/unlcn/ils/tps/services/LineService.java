package com.unlcn.ils.tps.services;

import java.util.Date; 
import java.util.List;

import com.unlcn.ils.tps.E_city;
import com.unlcn.ils.tps.E_line;
import com.unlcn.ils.tps.E_province;
import com.unlcn.ils.tps.E_transway;
import com.unlcn.ils.tps.common.TpsRuntimeException;
import com.unlcn.ils.tps.ininterface.SqlInterface;


import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;

public class LineService { 
	/*检查线路数据后，返加正确的实体给下一步骤完成保存
		入参：E_line实体
	*/
	public E_line checkData(E_line data) throws TpsRuntimeException
	{
		
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		//检查数据
		if  (data.getStartCityId() == null)
			throw new TpsRuntimeException("1001");
		if  (data.getDestProvinceId() == null)
			throw new TpsRuntimeException("错误的目的地省份，请检查数据");
		
		//得到城市名称
		int cityid = data.getStartCityId();
		E_city city = new E_city();
		city.setLineid(cityid);
		Dao<E_city> dao=DaoFactory.create(E_city.class);
		city = dao.selectOne(city);
		String cityno = city.getCityno();
		data.setStartCity(city.getCityname());
		E_province province=new E_province();
		province.setLineid(city.getProvinceid());
		data.setStartProvinceId(city.getProvinceid());
		data.setStartProvince(city.getProvincename());
		
		//得到目的地省份
		int provinceid = data.getDestProvinceId();
		province = new E_province();
		province.setLineid(provinceid);
		Dao<E_province> daoprovince=DaoFactory.create(E_province.class);
		province = daoprovince.selectOne(province);
		data.setDestProvince(province.getProvincename());
		
		//设置创建时间及创建人
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		data.setCreateTime(currdate);
		data.setActive(1);
		data.setCreateUser(username);
		
		//定义线路编号
		String provinceno=province.getProvinceno();		
		data.setLineno(cityno+"_"+provinceno);
		
		if ( data.getLineid() == null)
		{
			SqlInterface sqlinterface = new SqlInterface();
			String lineid= sqlinterface.getNextVal("SEQ_TPS_LINE");
			data.setLineid(lineid);			
		}
		
		
		//检查线路编号是否重复
		Dao<E_line> daoline=DaoFactory.create(E_line.class);
		
		
		E_line line= new E_line();
		
		
		
		line.setLineno(data.getLineno());
		E_transway transway=new E_transway();
		transway.setLineid(data.getTransway().getLineid());
		line.setTransway(transway);
		line.setActive(1);
		line = daoline.selectOne(line);
		if ( line !=null)
		{
			throw new TpsRuntimeException("线路重复");			
		}
		return data;
		
	}
	
	/*删除线路函数
	入参：E_line实体
	 */
	public boolean delete(List<String> idList)
	{	
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();		
		String username = context.getUser().getName();
		Dao<E_line> dao=DaoFactory.create(E_line.class);
		for(int i=0;i<idList.size();i++){
			String lineid = idList.get(i);
			E_line line = new E_line();
			line.setLineid(lineid);
			line.setActiveMemo("取消");
			line.setActiveUser(username);
			line.setActive(0);
			dao.update(line);
		}		
		return true;
	}
}

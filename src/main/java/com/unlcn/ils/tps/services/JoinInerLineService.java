package com.unlcn.ils.tps.services;
 
import java.util.List;
import java.util.Map;


import com.unlcn.ils.tps.E_join_driver;
import com.unlcn.ils.tps.E_join_interline;


import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/**
 * 
 *@Title:
 *@Description:意向线路服务
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class JoinInerLineService implements ArrayContentProvider{

	@SuppressWarnings("unused")
	@Override
	
	/**
	 * @Description：查看意向线路；
	 */
	public Page<E_join_interline> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Map<String, Object> conditions = context.getCondition();
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;		
		Dao<E_join_interline> data=DaoFactory.create(E_join_interline.class);		
		Dao<E_join_driver> driverdata=DaoFactory.create(E_join_driver.class);	
		Page<E_join_interline> sharelist = data.selectPageByCondition(conditions, 
				condition,context.getPageable(),context.getSortable(),true);
		List<E_join_interline> lists=sharelist.getContents();
		E_join_interline fleetshare = sharelist.getContents().get(0);
		lists.add(fleetshare);
		sharelist.setContents(lists);
		return sharelist;
	}

}

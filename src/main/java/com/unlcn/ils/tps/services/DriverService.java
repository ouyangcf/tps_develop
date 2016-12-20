package com.unlcn.ils.tps.services;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_join_driver;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;
/**
 * 
 *@Title:
 *@Description:加盟服务 
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class DriverService implements TreeContentProvider{
	/**
	 * 通过加盟申请表来加载分供方名称，用于加盟初审时搜索列中的分供方名称下拉框；
	 */
	@SuppressWarnings({ "static-access", "unused" })
	@Override
	public TreeNode[] getElements(TreeContext context) {
		// TODO Auto-generated method stub
		context.getConditions();//得到默认带过来的条件
		List<TreeNode> result = new ArrayList<TreeNode>();
		Dao<E_join_driver> driverDao=new DaoFactory().create(E_join_driver.class); 
		if ( context.getPid() == null)
		{//第一次加载
			//得到分供方
			List<E_join_driver> lists=driverDao.selectAll();
			List<Map<String,String>> drivers=new ArrayList<Map<String,String>>();
			for(int i=0;i<lists.size();i++){
				DefaultTreeNode node = new DefaultTreeNode("root_0",
						lists.get(i).getLineid().toString(),lists.get(i).getDriverName(),false);				
				result.add(node);
			}	
		}
		return result.toArray(new TreeNode[0]);		
	}

}

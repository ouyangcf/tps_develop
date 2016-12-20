package com.unlcn.ils.tps.services;

import java.util.ArrayList; 
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;
import com.unlcn.ils.tps.ininterface.ShipperInterface;

public class ShipperConditionService implements TreeContentProvider{
	private static  final Logger log = Logger.getLogger(Logger .class);
	
	public TreeNode[] getElements (TreeContext context)
	{

		log.info("调用生成分供方树形数据源方法");
		try 
		{
			context.getConditions();//得到默认带过来的条件
			List<TreeNode> result = new ArrayList<TreeNode>();
			if ( context.getPid() == null)
			{//第一次加载
				//得到分供方
				List<Map<String,String>> shipperlist = ShipperInterface.getData(null);	
				log.info("获取crm的分供方信息，其长度为："+shipperlist.size());
				for(int i = 0;i<shipperlist.size();i++)
				{
					Map<String,String> shipper = shipperlist.get(i);
					log.info("我要返回"+shipper.get("certStatus"));
					if(shipper.get("certStatus").equals("认证通过")){
					String lineid =shipper.get("id");
					String name =shipper.get("name");
					DefaultTreeNode node = new DefaultTreeNode("root_0",
							lineid,name,false);			
					result.add(node);
					}
				}
			}
			log.info("返回树形数据源");
			return result.toArray(new TreeNode[0]);				
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			throw new RuntimeException("分供方接口调用错误");
		}
		
	}
}

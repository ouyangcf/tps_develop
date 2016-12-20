package com.unlcn.ils.tps.services;
 
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;
import com.unlcn.ils.tps.ininterface.*;
/**
 * 
 *@Title:
 *@Description:分供方服务 
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class ShipperService  implements TreeContentProvider {
	private static  final Logger log = Logger.getLogger(Logger .class);
	@Override
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
					String lineid =shipper.get("id");
					String name =shipper.get("name");
					DefaultTreeNode node = new DefaultTreeNode("root_0",
							lineid,name,false);			
					result.add(node);
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
	/**
	 * 
	 * @param shippperid
	 * @return
	 * @Description:根据id获取分供方信息
	 */
	public Map<String,String> getShipperById(String shippperid)
	{
		ShipperInterface.getShipperByID(shippperid);
		CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
		Map<String, String> oneship=crmInformationInterface.getShipperFromCrmById(shippperid);
		return oneship;
		
	}
	/**
	 * 
	 * @return
	 * @Description:更新分供方
	 */
	public String updateShipper()
	{
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		Map<String, Object> input = context.getInput();
		input.get("postdata");
		return "0";
		
	} 
	/**
	 * 
	 * @return
	 * @Description:获取分供方信息
	 */
	public List<Map<String,String>> getShipperInfo(){
		
		return ShipperInterface.getData(null);
	}
}

package com.unlcn.ils.tps.services;

import java.util.ArrayList; 
import java.util.List; 
import java.util.Map;

import com.unlcn.ils.tps.ininterface.CustomerInterface;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;
/**
 * 
 *@Title:
 *@Description:客户服务
 *@Author:Administrator 
 *@Since:2016-8-19
 *@Version:1.1.0
 */ 
public class CustomerService  implements TreeContentProvider {
	@Override
	public TreeNode[] getElements (TreeContext context)
	{
		context.getConditions();//得到默认带过来的条件
		List<TreeNode> result = new ArrayList<TreeNode>();
		if ( context.getPid() == null)
		{//第一次加载
			//得到客户
			List<Map<String,String>> shipperlist = CustomerInterface.getData(null);			
			for(int i = 0;i<shipperlist.size();i++)
			{	
				Map<String,String> shipper = shipperlist.get(i);
				String lineid =shipper.get("id");
				String name =shipper.get("name");
			//String lineid="C1";
			//String name="江铃汽车股份有限公司";
				DefaultTreeNode node = new DefaultTreeNode("root_0",
						lineid,name,false);			
				result.add(node);
			}
			
		}
		return result.toArray(new TreeNode[0]);			
	}
	/**
	 * 
	 * @param customerid
	 * @return
	 * @Description:根据客户id获取客户信息
	 */
	public Map<String,String> getCustomerById(String customerid)
	{
		Map<String,String> customer = CustomerInterface.getCustomerByID(customerid);	
		return customer;
		
	} 
}

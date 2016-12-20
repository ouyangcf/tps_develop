package com.unlcn.ils.tps.services;

import java.util.ArrayList;  
import java.util.List;
import org.apache.log4j.Logger;

import com.chinacreator.asp.comp.sys.advanced.user.service.UserService;
import com.chinacreator.asp.comp.sys.core.user.dto.UserDTO;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;

public class QutoationCheckUserService implements TreeContentProvider{

	private static  final Logger log = Logger.getLogger(Logger .class);
	@Override
	public TreeNode[] getElements(TreeContext context) {

		try 
		{
			context.getConditions();//得到默认带过来的条件
			List<TreeNode> result = new ArrayList<TreeNode>();
			
			 UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
			   UserDTO ud = new UserDTO();
					   ud.setUserType("0");
			List<UserDTO> userList=new ArrayList<UserDTO>();  
					   userList=userService.queryByUser(ud);
			
			if ( context.getPid() == null)
			{//第一次加载
				//得到用户
				

				log.info("获取用户，其长度为："+userList.size());
				for(int i = 0;i<userList.size();i++)
				{	String lineid =userList.get(i).getUserName();
					String name =userList.get(i).getUserRealname();
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
			throw new RuntimeException("获取用户错误");
		}
		
	}

}

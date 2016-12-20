package com.unlcn.ils.tps.services;

import java.util.ArrayList; 
import java.util.List;
import org.apache.log4j.Logger;

import com.unlcn.ils.tps.E_line;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;

public class LineTreeService implements TreeContentProvider{
	private static  final Logger log = Logger.getLogger(Logger .class);
	@Override
	public TreeNode[] getElements(TreeContext context) {

		log.info("调用线路树形数据源方法");
		try 
		{
			context.getConditions();//得到默认带过来的条件
			List<TreeNode> result = new ArrayList<TreeNode>();
			Dao<E_line> lineDao=DaoFactory.create(E_line.class) ;
			if ( context.getPid() == null)
			{//第一次加载
				//得到线路
				
				List<E_line> Linelist =lineDao.selectAll();	
				//List<Map<String,String>> lineMaps=new ArrayList<Map<String,String>>();
				log.info("获取线路，其长度为："+Linelist.size());
				for(int i = 0;i<Linelist.size();i++)
				{
					E_line line = Linelist.get(i);
					if(line.getActive()==1){
						String lineid =line.getLineid();
						String name =line.getStartCity()+"→"+line.getDestProvince();
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
			throw new RuntimeException("获取线路错误");
		}
		
	}
	

}

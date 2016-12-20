package com.unlcn.ils.tps.services;  

import java.util.List; 
import com.unlcn.ils.tps.E_levelchange;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
/**
 * 
 *@Title:
 *@Description:审核标志服务
 *@Author:johnn
 *@Since:2016-8-18
 *@Version:1.1.0 
 */
public class ApproveStatusService {
	/**
	 * 
	 * @param lineid
	 * @return
	 * @Description:将级别调整后的数据提交
	 */
	@SuppressWarnings("static-access")
	public boolean setApproveStatus(List<String>  lineid)
	{  for(int i=0;i<lineid.size();i++)
		{
		Dao<E_levelchange> levelchangeDao=new DaoFactory().create(E_levelchange.class);
		String selectByLevelTable_LineIDFullPath="com.unlcn.ils.tps.E_levelchangeMapper.selectByLevelTable_LineID";
		List<E_levelchange> levelchangeList=levelchangeDao.getSession().selectList(selectByLevelTable_LineIDFullPath,lineid.get(i));
		levelchangeList.get(levelchangeList.size()-1).setApproveStatus(2);
		levelchangeDao.update(levelchangeList.get(levelchangeList.size()-1));
		}
		return true;
}

}

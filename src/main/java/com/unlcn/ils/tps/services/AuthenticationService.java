package com.unlcn.ils.tps.services;

import java.sql.Timestamp; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.unlcn.ils.tps.E_authentication;
import com.unlcn.ils.tps.E_authentication_file;
import com.unlcn.ils.tps.E_authentication_type;
import com.unlcn.ils.tps.ininterface.ShipperInterface;
import com.unlcn.ils.tps.ininterface.SqlInterface;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.fs.FileServer;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/**
 * 
 *@Title:
 *@Description:二次认证服务 
 *@Author:Administrator 
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class AuthenticationService  implements ArrayContentProvider{
	private static Logger logger=Logger.getLogger(Logger.class);
	@Override
	public Page<?> getElements(ArrayContext context)
	{
		logger.info("正在调用获取数据函数getElements");
		Map<String, Object> conditions = context.getCondition();
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;		
		
		
		List<Map<String, String>> listshipper= ShipperInterface.getDataByRules(condition); 
		logger.info("获取的分供方长度为:"+listshipper.size());
		int pagesize = context.getPageable().getPageSize();
		int pageindex = context.getPageable().getPageIndex();
		logger.info("获取的pagesize为"+pagesize);
		logger.info("获取的pageindex为"+pageindex);
		Page<Map<String,String>> resultdata = new Page<Map<String,String>>();
		resultdata.setPageIndex(pageindex);
		resultdata.setPageSize(pagesize);
		resultdata.setTotal(listshipper.size());
		List<Map<String, String>>  resultList = getDataByPage(pageindex, pagesize, listshipper);
		resultdata.setContents(resultList);
		return resultdata;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public String authenadd()
	{
		logger.info("调用authenadd方法");
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		
		String username = context.getUser().getName();
		
		Map<String, Object> input = context.getInput();
		Integer filecount=  (Integer)input.get("filecount");
		Map<String, String> data= (Map<String, String>) input.get("postdata");//认证文本
		
		List<Map<String, String>> filedata=null;
		if (filecount>1) //多个文件
		{
			filedata = (List<Map<String, String>>) input.get("filedata") ;//上传附件
		}
		else//单个文件或0个文件
		{
			filedata=  new ArrayList<Map<String, String>>();
			filedata.add((Map<String, String>) input.get("filedata"));
		}
		
		String authresult= (String) input.get("result") ;//认证结果
		//分供方id
		String shipperid=  (String)input.get("shipper_id");
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		
		Dao<E_authentication> dao=DaoFactory.create(E_authentication.class);
		String userno=username;
		E_authentication auth = null;
		
		Timestamp enddate2 =null;
		
		SqlInterface sqlinterface = new SqlInterface();
		String lineid= sqlinterface.getNextVal("SEQ_TPS_AUTHENTICATION");//取流水号
		logger.info("已经获取流水号");
		
		if (authresult.equals("yes"))
		{//认证通过
			String authenticationMemo  = data.get("okmemo");	
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			if  (StringUtils.isEmpty(data.get("begindate")))
				throw new RuntimeException("错误的有效日期，请检查数据");
			if  (StringUtils.isEmpty(data.get("enddate") ))
				throw new RuntimeException("错误的失效日期，请检查数据");
			
			
			String begindate = sdf.format(java.sql.Date.valueOf(data.get("begindate")));
			String enddate = sdf.format(java.sql.Date.valueOf(data.get("enddate")));
			Timestamp begindate2 = Timestamp.valueOf(begindate);
			enddate2 = Timestamp.valueOf(enddate);
			
			//检查有效期
			if ( begindate2.after(enddate2) )
			{
				throw new RuntimeException("错误的有效期，请检查数据");
			}
			
			//保存认证表
			auth = new E_authentication();
			
			auth.setActive(1);
			
			auth.setLineid(lineid);
			auth.setShippperId(shipperid);
			auth.setAuthenticationFlag(1);
			auth.setAuthenticationUser(userno);
			auth.setAuthenticationMemo(authenticationMemo);
			auth.setAuthenticationTime(currdate);
			auth.setBeginDate(begindate2);
			auth.setEndDate(enddate2);
			auth.setCreateTime(currdate);
			auth.setCreateUser(userno);
			dao.insert(auth);
		}
		else
		if (authresult.equals("no"))
		{//认证不通过
			String authenticationMemo  = data.get("cancelmemo");
			String reasonid  = data.get("reason");
			//保存认证表
			auth = new E_authentication();
			auth.setLineid(lineid);
			auth.setActive(1);
			auth.setShippperId(shipperid);
			auth.setAuthenticationFlag(2);
			auth.setAuthenticationUser(userno);
			auth.setAuthenticationMemo(authenticationMemo);
			auth.setAuthenticationTime(currdate);
			auth.setBeginDate(null);
			auth.setEndDate(null);
			
			auth.setCreateTime(currdate);
			auth.setCreateUser(userno);
			E_authentication_type type =new E_authentication_type();   
			type.setLineid(Integer.parseInt(reasonid));
			auth.setAuthenticationReasonid(type);
			dao.insert(auth);
		}
		logger.info("准备调用分供方接口");
		//更新分供方最新认证表
		//调用接口
		String result = ShipperInterface.saveauth(shipperid, enddate2, authresult); 
		logger.info("获取返回结果");
		if ( !result.equals("ok") )
		{
			try {//删除文件
				deletefile(filedata);
			} catch (Exception e) {
				e.printStackTrace();
			}
			throw new RuntimeException("更新分供方信息错误，原因："+result);
		}
		//保存上传附件
		if ( filedata.size() >  0)
		{
			logger.info("正在处理获取附件章程");
			String filelineid = sqlinterface.getNextVal("SEQ_TPS_AUTHENTICATIONFILE");//取流水号  
			for (int i =0;i<filedata.size();i++)
			{
				Map<String,String> file = filedata.get(i);
				if ( file==null) continue;//没有文件
				String filesize =String.valueOf(file.get("filesize"));
				String mimetype = file.get("mimetype");
				String filename = file.get("name");
				String path = file.get("path");
				String url = file.get("url");
			
				E_authentication_file authfile = new E_authentication_file();
				authfile.setAuthenticationId(auth);
				authfile.setLineid(filelineid);
				authfile.setActive(1);
				authfile.setCreateTime(currdate);
				authfile.setCreateUser(userno);
				authfile.setFileName(filename);
				authfile.setFileUrl(url);
				authfile.setFileType(mimetype);
				authfile.setFilePath(path);
				authfile.setFileMemo(url);
				authfile.setFileSize(Integer.parseInt(filesize));
				Dao<E_authentication_file> daofile=DaoFactory.create(E_authentication_file.class);
				daofile.insert(authfile);
			}
		}
		return "ok";
	} 
	/**
	 * 
	 * @param files
	 * @throws Exception
	 * @Description:删除附件服务
	 */
	public void deletefile(List<Map<String, String>>  files) throws Exception
	{
		FileServer dirServer = ApplicationContextManager.getContext().getBean("dirFileServer", FileServer.class);
		if ( files.size() >  0)
		{
			for (int i =0;i<files.size();i++)
			{
				Map<String,String> file = files.get(i);
				if ( file==null) continue;//没有文件
				String filepath = file.get("path");
				dirServer.delete(filepath, true);
			}
		}
		
		
		
	}
	
	private List<Map<String, String>> getDataByPage(int page,int size,List<Map<String, String>> datas){
		logger.info("正在调用getDataByPage私有方法");
		logger.info("传参page为"+page);
		logger.info("传参size为"+size);
		logger.info("传参datas长度为"+datas.size());
		List<Map<String, String>> returnList=new ArrayList<Map<String, String>>();
		int min=page*size<=datas.size()?page*size:datas.size();
		logger.info("参数min为"+min);
		logger.info("调用for循环之前");
		for(int i=(page-1)*size;i<min;i++){
			logger.info("获取的分供方名称为"+datas.get(i).get("name"));
			returnList.add(datas.get(i));
		}
		return returnList;
	}
}

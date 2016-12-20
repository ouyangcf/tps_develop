package com.unlcn.ils.tps.services;
 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

import com.unlcn.ils.crm.dto.Result;

import com.unlcn.ils.tps.E_account;
import com.unlcn.ils.tps.E_audit;
import com.unlcn.ils.tps.E_city; 
import com.unlcn.ils.tps.E_currentlevel;
import com.unlcn.ils.tps.E_join_driver;
import com.unlcn.ils.tps.E_join_file;
import com.unlcn.ils.tps.E_join_intentline;
import com.unlcn.ils.tps.E_level;
import com.unlcn.ils.tps.E_linkman;
import com.unlcn.ils.tps.E_province;
import com.unlcn.ils.tps.ininterface.CrmInformationInterface;
import com.unlcn.ils.tps.ininterface.SqlInterface;

public class JoinDriverService implements ArrayContentProvider{
	WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	String username = context.getUser().getName();
	private static Logger logger=Logger.getLogger(Logger.class);
	
	/**
	 * @Description：检查传进来的分供方信息，用于保存至分供方加盟申请表中；
	 * @param data
	 * @return
	 */
	public E_join_driver checkdata(E_join_driver data){
		
		//得到城市名称
	    int cityid = data.getCityId();
	    if(cityid!=0)
	    { 
	    	E_city city = new E_city();
	    	city.setLineid(cityid);
			Dao<E_city> dao=DaoFactory.create(E_city.class);
			city = dao.selectOne(city);
			data.setCity(city.getCityname());
				
			//得到省份
			int provinceid = city.getProvinceid();
			E_province province = new E_province();
			province.setLineid(provinceid);
			Dao<E_province> daoprovince=DaoFactory.create(E_province.class);
			province = daoprovince.selectOne(province);
			data.setProvinceId(provinceid);
			data.setProvince(province.getProvincename());
	    }
	    else{
	    	throw new RuntimeException("注册城市不能为空");
	    };
	    
	    //判断司机姓名
	    if(data.getDriverName() == null || data.getDriverName().equals(""))
		{
			throw new RuntimeException("司机姓名不能为空");
		};
		
		//判断车牌号
		if(data.getLicenseno() == null || data.getLicenseno().equals(""))
		{
			throw new RuntimeException("车牌号不能为空");
		};
		
		//判断行驶证号
		if(data.getVehicleLicenseno() == null || data.getVehicleLicenseno().equals(""))
		{
			throw new RuntimeException("车牌号不能为空");
		};
		
		//判断身份证号
		if(data.getCardno() == null || data.getCardno().equals(""))
		{
			throw new RuntimeException("身份证号不能为空");
		};
		
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		data.setCreateTime(currdate);
		data.setCreateUser(username);
		data.setActive(1);
		data.setCheckFlag(0);
		
		return data;
		
	}

	@Override
	/**
	 * @Description：用于分供方合作搜索，进行搜索的后台处理；
	 */
	public Page<E_join_driver> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_join_driver> dao = DaoFactory.create(E_join_driver.class);
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_join_driver> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		
		return result;
	}
	/**
	 * @Description：创建分供方合作申请信息并进行检查；
	 * @Description：将分供方合作基本信息保存至合作申请表中、意向线路保存至意向线路表中；
	 * @param data
	 * @param startCity
	 * @param endCity
	 * @param price
	 * @param qty
	 * @return
	 */
	@SuppressWarnings("unused")
	public boolean createDriver(E_join_driver data,
			String[] startCity,String[] endCity,
			String[] price,String[] qty,
			List<E_linkman> linkmanList, List<E_account> accountList,
			String urlString,String nameString){
		//得到城市名称
		final Logger log = Logger.getLogger(Logger .class);
	    int cityid = data.getCityId();
	    Dao<E_join_driver> driverDao=DaoFactory.create(E_join_driver.class);
	    Dao<E_join_intentline> interlineDao=DaoFactory.create(E_join_intentline.class);
	    Dao<E_join_file> joinfileDao=DaoFactory.create(E_join_file.class);
	    Dao<E_city> cityDao=DaoFactory.create(E_city.class);
	    Dao<E_province> provinceDao=DaoFactory.create(E_province.class);
	    Dao<E_account> accountDao=DaoFactory.create(E_account.class);
	    Dao<E_linkman> linkmanDao=DaoFactory.create(E_linkman.class);
	    Dao<E_audit> auditDao=DaoFactory.create(E_audit.class);
	    Dao<E_level> levelDao=DaoFactory.create(E_level.class);
	    Dao<E_currentlevel> currentDao=DaoFactory.create(E_currentlevel.class);
	    if(cityid!=0)
	    {
	    	E_city city = new E_city();
	    	city.setLineid(cityid);
			Dao<E_city> dao=DaoFactory.create(E_city.class);
			city = dao.selectOne(city);
			data.setCity(city.getCityname());
				
			//得到省份
			int provinceid = city.getProvinceid();
			E_province province = new E_province();
			province.setLineid(provinceid);
			Dao<E_province> daoprovince=DaoFactory.create(E_province.class);
			province = daoprovince.selectOne(province);
			data.setProvinceId(provinceid);
			data.setProvince(province.getProvincename());
	    }
	    else{
	    	throw new RuntimeException("注册城市不能为空");
	    };
	    
	    //判断司机姓名
	    if(data.getDriverName() == null || data.getDriverName().equals(""))
		{
			throw new RuntimeException("司机姓名不能为空");
		};
		
		//判断车牌号
		if(data.getLicenseno() == null || data.getLicenseno().equals(""))
		{
			throw new RuntimeException("车牌号不能为空");
		};
		
		//判断行驶证号
		if(data.getVehicleLicenseno() == null || data.getVehicleLicenseno().equals(""))
		{
			throw new RuntimeException("行驶证号不能为空"); 
		};
		
		//判断身份证号
		if(data.getCardno() == null || data.getCardno().equals(""))
		{
			throw new RuntimeException("身份证号不能为空");
		};
		//判断可用车辆数
		if(data.getCarNumber() == null || data.getCarNumber().equals("")){
			
			throw new RuntimeException("可用车辆数不能为空");
		};
		//创建时间、创建人、取消标志、默认的初审状态、Lineid设置；
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		data.setCreateTime(currdate);
		data.setCreateUser(username);
		data.setActive(1);
		data.setCheckFlag(0);
		SqlInterface sqlInterface=new SqlInterface();
		String driverLineid=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_DRIVER");
		data.setLineid(driverLineid);
		String driverName=data.getDriverName();
		String carno=data.getCardno();
		String realName=driverName+carno;
		data.setDriverName(realName);
		driverDao.insert(data);
		//获取刚刚插入的司机表主键
		String getMaxIdPath="com.unlcn.ils.tps.E_join_driverMapper.getMaxId";
		List<E_join_driver> drivers=driverDao.getSession().selectList(getMaxIdPath);
		//插入意向线路表
		for(int i=0;i<startCity.length;i++){
			E_join_intentline e_join_intentline=new E_join_intentline();
			e_join_intentline.setStartCity(startCity[i]);
			e_join_intentline.setDestProvince(endCity[i]);
			if(!price[i].equals("")){
				BigDecimal bigDecimal=new BigDecimal(price[i]);
				e_join_intentline.setPrice(bigDecimal);				
			}
			if (!qty[i].equals("")) {
				e_join_intentline.setQty(Integer.parseInt(qty[i]));				
			}
			java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
			e_join_intentline.setCreateTime(timestampnow);
			e_join_intentline.setCreateUser(username);
			e_join_intentline.setActive(0);
			e_join_intentline.setJoinId(drivers.get(0).getLineid());
			e_join_intentline.setJoinFlag(0);
			String intentLineid=sqlInterface.getNextVal("SEW_TPS_TPS_JOIN_INTERLINE");
			e_join_intentline.setLineid(intentLineid);
			interlineDao.insert(e_join_intentline);
		}
		//插入账户信息
		if((accountList != null) && (accountList.size()>0)){
			//E_account e_account = new E_account();
			for(E_account e_account : accountList ){
				String accountLineid=sqlInterface.getNextVal("SEQ_TPS_ACCOUNT");
				//log.info("我要返回账户信息的序号是"+accountLineid);
				e_account.setId(accountLineid);
				e_account.setCustomerId(driverLineid); 
				//e_account.setOwnerType("个人");
				e_account.setOperUser(username);
				e_account.setFlag(0);
				e_account.setStatus("1");
				//log.info("我要返回"+e_account.getOperUser());
				java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
				e_account.setOperTime(timestampnow);
			}
			accountDao.insertBatch(accountList);
		}
		
		//插入联系人信息
		if((linkmanList != null) && (linkmanList.size()>0)){
			//E_account e_account = new E_account();
			for(E_linkman e_linkman : linkmanList ){
				String linkmanLineid=sqlInterface.getNextVal("SEQ_TPS_LINKMAN");
				//log.info("我要返回账户信息的序号是"+linkmanLineid);
				e_linkman.setId(linkmanLineid);
				e_linkman.setCustomerId(driverLineid);
				e_linkman.setServiceType("");
				e_linkman.setOperUser(username);
				e_linkman.setFlag(0);
				//log.info("我要返回"+e_linkman.getOperUser());
				java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
				e_linkman.setOperTime(timestampnow);
			}
			linkmanDao.insertBatch(linkmanList);
		}
		
		//插入附件信息
		
		E_join_file e_join_file=new E_join_file();
		e_join_file.setFileName(nameString);
		e_join_file.setFilePath(urlString);
		String sourceStr=nameString;
		Integer pos=sourceStr.indexOf('.');
        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
        e_join_file.setFileExt(substring);
        e_join_file.setSourceId(driverLineid);
        e_join_file.setFlag(0);
		java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
		e_join_file.setCreateTime(timestampnow);
		e_join_file.setCreateUser(username);
		String filelineid=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_FILE");
		e_join_file.setLineid(filelineid);
		joinfileDao.insert(e_join_file);
		
		//插入初审表
		E_audit e_audit=new E_audit();
		e_audit.setJoinId(driverLineid);	
		e_audit.setJoinFlag(0);
		e_audit.setAuditFlag(0);
		//java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
		e_audit.setCreateTime(timestampnow);
		e_audit.setCreateUser(username);
		e_audit.setActive(0);
		String auditLineid=sqlInterface.getNextVal("SEQ_TPS_TPS_AUDIT");
		e_audit.setLineid(auditLineid);
		auditDao.insert(e_audit);
		
		return true;
		 
		
	}
	
	public List<String> selectByCityId(Integer cityId){
		Dao<E_join_driver> driverDao=DaoFactory.create(E_join_driver.class);
		String getMaxIdPath="com.unlcn.ils.tps.E_join_driverMapper.selectByCityId";
		List<E_join_driver> drivers=driverDao.getSession().selectList(getMaxIdPath,cityId);
		List<String> list=new ArrayList<String>();
		for(int i=0;i<drivers.size();i++){
			list.add(drivers.get(i).getLineid());
		}
		return list;
	}
	
	/**
	 * @Description：初审时分供方信息编辑，更新加盟申请表信息；
	 * @param data
	 * @return
	 */
	public boolean updateDriver(E_join_driver data){
		logger.info("调用方法updateDriver");
		Dao<E_join_driver> driverDao=DaoFactory.create(E_join_driver.class);
		Dao<E_city> cityDao=DaoFactory.create(E_city.class);
		E_join_driver e_join_driver=driverDao.selectByID(data.getLineid());
		//E_join_driver driver=driverDao.selectByID(data.getLineid());
		e_join_driver.setDriverName(data.getDriverName());
		e_join_driver.setCarNumber(data.getCarNumber());
		e_join_driver.setCityId(data.getCityId());
		e_join_driver.setProvinceId(cityDao.selectByID(data.getCityId()).getProvinceid());
		e_join_driver.setProvince(cityDao.selectByID(data.getCityId()).getProvincename());
		e_join_driver.setCity(cityDao.selectByID(data.getCityId()).getCityname());
		e_join_driver.setLicenseno(data.getLicenseno());
		e_join_driver.setVehicleLicenseno(data.getVehicleLicenseno());
		e_join_driver.setCardno(data.getCardno());
		e_join_driver.setMobileno(data.getMobileno());
		//driverDao.update(e_join_driver);
		logger.info("更新完成");
		if (e_join_driver.getCheckFlag()==null) {
			logger.info("data.getCheckFlag()为空");
		}
		else{
			logger.info("data.getCheckFlag()不为空");
		}
		if(e_join_driver.getCheckFlag()==3){
			//下面更新crm方信息
			CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
			Result result=new Result(1, null, "");
			 try {
				result=crmInformationInterface.updateShipper2Crm_d(e_join_driver);
				if(result.getRetCode()==-1){
					logger.info("调用失败,错误码为: "+result.getErrCode());
					logger.info("调用失败,错误信息为: "+result.getErrDesc());
					//driverDao.update(driver);
					throw new RuntimeException("crm方更新分供方失败,错误原因为:"+result.getErrDesc());
					}
					else{
						driverDao.update(e_join_driver);
					}
					
			} catch (Exception e) {
				//driverDao.update(driver);
				// TODO Auto-generated catch block
				if(result.getRetCode()==-1){
					throw new RuntimeException("crm方更新分供方失败,错误原因为:"+result.getErrDesc());
				}
				else{
					throw new RuntimeException("更新失败,请重试");
				}
				
			}
		}
		return false;
	}
	
	/**
	 * @Description：附件上传
	 * @param mapList
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public boolean driverAttached(Map mapList,String id){
		//一个附件对应一个一个数据
		Dao<E_join_file> authenticationDao=new DaoFactory().create(E_join_file.class);
		Dao<E_join_driver> driverDao=new DaoFactory().create(E_join_driver.class);
		List<Map> fileList=(List<Map>) mapList.get("fileList");
		E_join_driver e_join_driver=driverDao.selectByID(id);
		//单文件时
		if(fileList==null){
			E_join_file e_attached=new E_join_file();
			e_attached.setFileName(mapList.get("name").toString());
			e_attached.setFilePath(mapList.get("url").toString());
			String sourceStr=(String) mapList.get("name");
			Integer pos=sourceStr.indexOf('.');
	        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
			e_attached.setFileExt(substring);
			e_attached.setSourceId(e_join_driver.getLineid());
			e_attached.setFlag(0);
			java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
			e_attached.setCreateTime(timestampnow);
			e_attached.setCreateUser(username);
			SqlInterface sqlInterface=new SqlInterface();
			String lineid=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_FILE");
			e_attached.setLineid(lineid);
			authenticationDao.insert(e_attached);
			return true;
		}
		//多文件时
		else{
			for(int i=0;i<fileList.size();i++){
				E_join_file e_attached=new E_join_file();
				Map map=fileList.get(i);
				e_attached.setFileName(map.get("name").toString());
				e_attached.setFilePath(map.get("url").toString());
				String sourceStr=(String) map.get("name");
				Integer pos=sourceStr.indexOf('.');
		        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
				e_attached.setFileExt(substring);
				e_attached.setFlag(0);
				e_attached.setSourceId(e_join_driver.getLineid());
				java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
				e_attached.setCreateTime(timestampnow);
				e_attached.setCreateUser(username);
				SqlInterface sqlInterface=new SqlInterface();
				String lineid=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_FILE");
				e_attached.setLineid(lineid);
				authenticationDao.insert(e_attached);
			}
			return true;
		}
		}	
}

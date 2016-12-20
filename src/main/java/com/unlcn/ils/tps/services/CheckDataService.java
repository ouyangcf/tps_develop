package com.unlcn.ils.tps.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.unlcn.ils.tps.E_configure_detail;
import com.unlcn.ils.tps.E_method;

public class CheckDataService {
	public void createCheckDatas(String shipper,String check_configure,String checkCycle,String checkDate,
			String checkDate1,String  checkDate2,String checkDate3) {
		Date a=new Date(Long.valueOf(checkDate));
		if(shipper!=null&&shipper.length()!=0){
			E_configure_detail configure_detail=new E_configure_detail();
			configure_detail.setConfigureLineid(check_configure);
			Dao<E_configure_detail> configureDao=DaoFactory.create(E_configure_detail.class);

			//获取计划完成率数据
			Object[] pcr=checkByPCR(configure_detail, shipper);
				

			//获取交付及时率数据
			//获取规范操作数据
			//获取报班准确率数据
			//获取起运及时率数据
			//获取回单及时率数据
			//获取GPS数据

		}
	}
	/**
	 * 
	 * @param configure_detail
	 * @return
	 * @Description:根据计划完成率计算扣分扣款
	 */
	protected Object[] checkByPCR(E_configure_detail configure_detail ,String shipperLineid) {
		Dao<E_configure_detail> configureDao=DaoFactory.create(E_configure_detail.class);
		float f=0.98f;//假设完成率
		Integer num=100;//假设数量
		BigDecimal number=new BigDecimal(num);
		Dao<E_method> methodDao=DaoFactory.create(E_method.class);
		E_method method=new E_method();
		method.setTarget(1);
		List<E_method> e_methods=methodDao.select(method);
		configure_detail.setCalDescription(e_methods.get(0).getMethod());
		E_configure_detail e_configure_details = configureDao.selectOne(configure_detail);
		Integer value=e_configure_details.getTotalvalue();//总分
		Integer subvalue=e_configure_details.getSubvalue();//每下降百分点扣分
		BigDecimal submoney = new BigDecimal(0);//扣款
		float requirements=Float.parseFloat(e_configure_details.getRequirements());//指标要求
		float v=requirements-100*f;//指标要求与实际完成率的相差
		if(v<0){
			value=40;
			
		}
		else{
			Integer i=Math.round(v); 
			value=value-i*subvalue;//完成率最终得分
			for(int j=0;j<e_methods.size();j++){
				Double leftNode=e_methods.get(j).getLeftNode();
				Double rightNode=e_methods.get(j).getRightNode();
				if(!leftNode.equals("")&&!rightNode.equals("")){//如0~5,5~10
					if(leftNode<=v&&v<rightNode)
						submoney=e_methods.get(j).getSubMoney().multiply(number);
				}
				if(!rightNode.equals("")){//如15~
					if(leftNode<=v)
						submoney=e_methods.get(j).getSubMoney().multiply(number);
				}
			}
			
		}
		Object[] returnArray={value,submoney};
		return returnArray;
	}
}

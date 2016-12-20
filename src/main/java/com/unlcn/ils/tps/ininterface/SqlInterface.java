package com.unlcn.ils.tps.ininterface;
  
import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;

import com.unlcn.ils.tps.E_sequence_data;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
  
public class SqlInterface {     
	@SuppressWarnings("unused")
	private static Logger logger=Logger.getLogger(Logger.class);
	@SuppressWarnings("static-access")
	private String nextVal(String str){
		String getCurVal="com.unlcn.ils.tps.E_sequence_dataMapper.getCurVal";
		Dao<E_sequence_data> dao=new DaoFactory().create(E_sequence_data.class);
		List<E_sequence_data> sequence_datas=dao.getSession().selectList(getCurVal, str);
		E_sequence_data e_sequence_data=sequence_datas.get(0);
		BigInteger lastVal=e_sequence_data.getSequenceCurValue();
		Integer increament=e_sequence_data.getSequenceIncrement();
		BigInteger bigIncreament=new BigInteger(increament.toString());
		BigInteger curVal=lastVal.add(bigIncreament);
		e_sequence_data.setSequenceCurValue(curVal);
		dao.update(e_sequence_data);
		return curVal.toString();
	}
	public String getNextVal(String str){
		return nextVal(str);
	}
}

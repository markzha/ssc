package com.zhangyan.myssc.dao.impl;

import java.util.List;

import com.zhangyan.myssc.bean.BaseData;
import com.zhangyan.myssc.dao.IBasedataDao;
import com.zhangyan.myssc.frame.NkContext;

public class BasedataDaoImpl implements IBasedataDao{

	@Override
	public List<BaseData> findAll() throws Exception {
		String sql = "select * from t_basedata t order by t.c_opentime_stamp;";
		return NkContext.getDbutilThread().get().getList(new BaseData(), sql, new String[]{});
	}

	@Override
	public boolean delAll() throws Exception {
		String sql = "delete from t_basedata;";
		NkContext.getDbutilThread().get().delAllObj(sql);
		return true;
	}

	@Override
	public void saveBBList(List<BaseData> bbList) throws Exception {
		if(null!=bbList && bbList.size()>0){
			for(BaseData bb : bbList){
				this.addOne(bb);
			}
		}
	}

	@Override
	public void addOne(BaseData bb) throws Exception {
		NkContext.getDbutilThread().get().addObj(bb);
	}

	@Override
	public boolean updateBB(BaseData bb) throws Exception {
		NkContext.getDbutilThread().get().updateObj(bb);
		return true;
	}

	@Override
	public BaseData findOneByName(String unitName) throws Exception {
		String sql = "select * from t_basedata t WHERE t.c_unit_name = ?;";
		return NkContext.getDbutilThread().get().getObject(new BaseData(), sql, new String[]{unitName});
	}
	
}

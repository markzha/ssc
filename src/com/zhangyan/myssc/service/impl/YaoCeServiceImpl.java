package com.zhangyan.myssc.service.impl;

import java.util.List;

import android.content.Context;

import com.zhangyan.myssc.bean.YaoCe;
import com.zhangyan.myssc.dao.IYaoCeDao;
import com.zhangyan.myssc.dao.impl.YaoCeDaoImpl;
import com.zhangyan.myssc.service.IYaoCeService;
public class YaoCeServiceImpl implements IYaoCeService{
	
	private IYaoCeDao yaoCeDao = new YaoCeDaoImpl();

	@Override
	public boolean delAll(Context mContext) throws Exception{
		return this.yaoCeDao.delAll();
	}

	@Override
	public List<YaoCe> findByBBId(Context mContext, String bbId) throws Exception{
		return this.yaoCeDao.findByBBId(bbId);
	}

	@Override
	public YaoCe findOneByName(Context mContext, String collName) throws Exception {
		return this.yaoCeDao.findOneByName(collName);
	}

	@Override
	public boolean updateYC(Context mContext, YaoCe yc) throws Exception {
		return this.yaoCeDao.updateYC(yc);
	}
	
	
	
}

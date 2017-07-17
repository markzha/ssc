package com.zhangyan.myssc.service.impl;

import java.util.List;

import android.content.Context;

import com.zhangyan.myssc.bean.YaoXin;
import com.zhangyan.myssc.dao.IYaoXinDao;
import com.zhangyan.myssc.dao.impl.YaoXinDaoImpl;
import com.zhangyan.myssc.service.IYaoXinService;

public class YaoXinServiceImpl implements IYaoXinService{
	
	private IYaoXinDao yaoXinDao = new YaoXinDaoImpl();

	@Override
	public boolean delAll(Context mContext) throws Exception {
		return this.yaoXinDao.delAll();
	}

	@Override
	public List<YaoXin> findByBBId(Context mContext, String bbId) throws Exception {
		return this.yaoXinDao.findByBBId(bbId);
	}

	@Override
	public YaoXin findOneByName(Context mContext, String collName) throws Exception {
		return this.yaoXinDao.findOneByName(collName);
	}

	@Override
	public boolean updateYX(Context mContext, YaoXin yx) throws Exception {
		return this.yaoXinDao.updateYX(yx);
	}
	
	

}

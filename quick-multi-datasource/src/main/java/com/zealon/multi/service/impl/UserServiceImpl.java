package com.zealon.multi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zealon.multi.dao.UserMapper;
import com.zealon.multi.datasource.DataSourceContextHolder;
import com.zealon.multi.datasource.DataSourceEnum;
import com.zealon.multi.datasource.aop.TargetDataSource;
import com.zealon.multi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 查询默认数据源
	 */
	@Override
	public List<Map<String, Object>> selectAllByMasterDB() {
		// TODO Auto-generated method stub
		return userMapper.selectAllByMasterDB();
	}

	/**
	 * 查询其它数据源
	 */
	@Override
	@TargetDataSource(dataSource = DataSourceEnum.OTHER)
	public List<Map<String, Object>> selectAllByOtherDB() {
		// TODO Auto-generated method stub
		return userMapper.selectAllByOtherDB();
	}
	
	/**
	 * 多数据源组合查询
	 */
	@Override
	public String selectAllDB() {
		// TODO Auto-generated method stub
		String masterDBResult = "masterDBResult:"+JSON.toJSONString(selectAllByMasterDB());
		
		//切换数据源查询
		DataSourceContextHolder.setTargetDataSource(DataSourceEnum.OTHER);
		String otherDBResult = "otherDBResult:"+JSON.toJSONString(selectAllByOtherDB());
		
		return masterDBResult+"<hr>"+otherDBResult;
	}

}

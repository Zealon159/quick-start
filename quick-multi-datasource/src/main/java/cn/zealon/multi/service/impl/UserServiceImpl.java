package cn.zealon.multi.service.impl;

import java.util.List;
import java.util.Map;

import cn.zealon.multi.dao.UserMapper;
import cn.zealon.multi.datasource.DataSourceContextHolder;
import cn.zealon.multi.datasource.DataSourceEnum;
import cn.zealon.multi.datasource.aop.TargetDataSource;
import cn.zealon.multi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;

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
		
		// 切换数据源查询
		DataSourceContextHolder.setTargetDataSource(DataSourceEnum.OTHER);
		String otherDBResult = "otherDBResult:"+JSON.toJSONString(selectAllByOtherDB());

		// 代码中中使用，必须调用移除，不然导致默认数据源获取不准确
		DataSourceContextHolder.resetDefaultDataSource();
		return masterDBResult+"<hr>"+otherDBResult;
	}

}

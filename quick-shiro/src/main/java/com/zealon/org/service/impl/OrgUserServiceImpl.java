package com.zealon.org.service.impl;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zealon.org.dao.OrgUserMapper;
import com.zealon.org.entity.OrgUser;
import com.zealon.org.service.OrgUserService;
import com.zealon.shiro.util.RedisService;

/**
 * 用户服务
 * @author zealon
 * @date 2018年3月1日
 */
@Service("userService")
public class OrgUserServiceImpl implements OrgUserService{

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());  
	
	@Autowired
	private OrgUserMapper orgUserMapper;
	
	@Autowired
	RedisService redisService;
	

	/**
	 * 获取当前用户所有角色
	 */
	public Set<String> getRolesByUser(String userId) {
		Set<String> set = new HashSet<String>();
		
		//这里进行查询数据库获取
//		List<String> list =orgRoleMapper.getRolesByUser(userId);
//		for(String str : list){
//			set.add(str);
//		}
		return set;
	}

	/**
	 * 获取当前用户所有权限
	 */
	public Set<String> getPermissionsByUser(String userId) {
		Set<String> set = new HashSet<String>();
		//这里进行查询数据库获取
//		List<String> list =orgPermissionMapper.getPermissionsByUser(userId);
//		for(String str : list){
//			set.add(str);
//		}
//		logger.debug("查询数据库:"+list.toString());
		return set;
	}
	
	/***
	 * 根据用户id获取用户信息
	 */
	public OrgUser getUserInfoByUserid(String userId){
		OrgUser user = orgUserMapper.getUserInfoByUserid(userId);
		return user;
	}
}

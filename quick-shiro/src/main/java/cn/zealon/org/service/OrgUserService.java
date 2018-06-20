package cn.zealon.org.service;

import java.util.Set;
import cn.zealon.org.entity.OrgUser;

public interface OrgUserService {
	/**
	 * 获取当前用户所有角色
	 */
	Set<String> getRolesByUser(String userId);
	
	/**
	 * 获取当前用户所有权限
	 */
	Set<String> getPermissionsByUser(String userId);
	
	/***
	 * 根据用户id获取用户信息
	 */
	OrgUser getUserInfoByUserid(String userId);
}

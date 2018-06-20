package cn.zealon.shiro.util;

import cn.zealon.org.entity.OrgUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;


public class UserUtil {
	
	/**
	 * 获取当前登录用户对象
	 * @return
	 */
	public static OrgUser getCurrentOrgUser(){
		OrgUser user = null;
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null){
			 user = (OrgUser)subject.getPrincipal();
		}
		return user;
	}
	
	/**
	 * 获取当前登录用户ID
	 * @return
	 */
	public static String getCurrentUserid(){
		String userId = "";
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null){
			userId = ((OrgUser)subject.getPrincipal()).getUserid();
		}
		return userId;
	}
	
	/**
	 * 获取当前登录用户名称
	 * @return
	 */
	public static String getCurrentUserName(){
		String userName = "";
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null){
			userName = ((OrgUser)subject.getPrincipal()).getUserName();
		}
		return userName;
	}
}

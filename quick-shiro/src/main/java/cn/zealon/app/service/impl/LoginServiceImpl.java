package cn.zealon.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import cn.zealon.app.service.LoginService;
import cn.zealon.shiro.api.ShiroCache;
import cn.zealon.shiro.util.RedisService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统登录服务类
 * @author zealon
 * @date 2018年2月15日
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
    RedisService redisService;

	/**
	 * 登录程序
	 */
	public Map<String, Object> doLogin(String loginName, String pwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean success = false;
		String msg = "";
		String errorCode = "";
		// 得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		
		if (subject.isAuthenticated()) {
			msg = "登录成功！";
			success = true;
		} else {
			UsernamePasswordToken token = new UsernamePasswordToken(loginName, pwd);
			try {
				// 4、登录，即身份验证
				subject.login(token);
				if (subject.isAuthenticated()) {
					msg = "登录成功！";
					success = true;
				}
			} catch (UnknownAccountException e) {
				msg = "找不到账户！";
				errorCode = e.toString();
			} catch (IncorrectCredentialsException e) {
				msg = "密码不匹配！";
				errorCode = e.toString();
			} catch (LockedAccountException e) {
				msg = "账户被锁定！";
				errorCode = e.toString();
			} catch (AuthenticationException e) {
				msg = "身份验证失败！";
				errorCode = e.toString();
			}
		}
		map.put("success", success);
		map.put("msg", msg);
		map.put("errorCode", errorCode);
		return map;
	}

	/**
	 * 登出系统
	 */
	public void doLogout() {
		Subject subject = SecurityUtils.getSubject();
		String sessionId = subject.getSession().getId().toString();
		String sessionCacheKey = ShiroCache.REDIS_SHIRO_CACHE + sessionId;
		redisService.delete(sessionCacheKey);
		subject.logout();
	}
}

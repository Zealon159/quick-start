package com.zealon.shiro;

import java.util.Set;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.zealon.org.entity.OrgUser;
import com.zealon.org.service.impl.OrgUserServiceImpl;
import com.zealon.shiro.util.RedisService;
import com.zealon.shiro.util.ShiroUserPwdUtil;

public class ShiroRealm extends AuthorizingRealm {

	private static Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
	
	@Lazy
	@Autowired
	private OrgUserServiceImpl userService;
	
	@Lazy
	@Autowired
	private RedisService redisService;

	public ShiroRealm(CacheManager cacheManager) {
        super(cacheManager);
    }
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String userid = (String) upToken.getPrincipal(); // 得到用户名
		String password = new String((char[]) upToken.getCredentials()); // 得到密码
		String encrityPwd = ShiroUserPwdUtil.generateEncryptPwd(userid, password);
		Object credentials = encrityPwd;

		logger.debug("读取数据库中的" + userid + "-" + "信息");

		OrgUser user = userService.getUserInfoByUserid(userid);
		if(user==null){
			throw new UnknownAccountException("用户不存在！");
		}
		//为了实现微信登录暂做修改 如果微信用户与数据库中的用户密码一致，允许登录：user.getUserPwd().equals(password)
		if (!(user.getUserPwd().equals(encrityPwd)||user.getUserPwd().equals(password))) {
			throw new IncorrectCredentialsException("用户密码错误！");
		}
		
		if(user.getEnabledState()==false){
			throw new LockedAccountException("用户已被禁用！");
		}

		ByteSource credentialsSalt = ByteSource.Util.bytes(userid);

		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, credentials, credentialsSalt, getName());

		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		OrgUser user = (OrgUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = null;
		//用户权限尝试缓存读取
		String key = "authorizationCache:"+user.getUserid();
		authorizationInfo = (SimpleAuthorizationInfo)redisService.getObject(key);
		//没有缓存，查询数据库获取权限
		if(authorizationInfo==null){
			authorizationInfo = new SimpleAuthorizationInfo();
			// 角色
			authorizationInfo.setRoles(userService.getRolesByUser(user.getUserid()));
			// 权限
			Set<String> permissions = userService.getPermissionsByUser(user.getUserid());
			authorizationInfo.setStringPermissions(permissions);
		}
		return authorizationInfo;
	}

	//设置当前用户缓存名称
	@Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		OrgUser shiroUser = (OrgUser) super.getAvailablePrincipal(principals);
        return shiroUser.getUserid();
    }
	
	/**
	 * Clear Shiro Cache.
	 */
	public void clearCache() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
	
	
	public static void main(String[] args) {
		ByteSource credentialsSalt = ByteSource.Util.bytes("zhang");
		Object pwd = "123";
		Object result = new SimpleHash("MD5", pwd, credentialsSalt, 3);
		System.out.println(result);
	}
}

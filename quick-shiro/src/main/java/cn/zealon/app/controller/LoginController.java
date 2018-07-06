package cn.zealon.app.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.zealon.app.service.impl.LoginServiceImpl;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统登录控制器
 * @author zealon
 * @date 2018年2月16日
 */
@Controller
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginServiceImpl loginService;
	
	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}
	
	@ResponseBody
	@RequestMapping("/doLogin")
	public Map<String,Object> doLogin(HttpServletRequest request,HttpServletResponse response,String username,String password,String remenber) throws UnsupportedEncodingException{
		String url = "/";
		if(WebUtils.getSavedRequest(request)!=null){
			url = WebUtils.getSavedRequest(request).getRequestUrl();
		}
		Map<String,Object> map = loginService.doLogin(username, password);
		if((Boolean)map.get("success")==true){
			if(url.equals("/") || url.equals("/favicon.ico")){
				url = "index";
			}
			map.put("url", url);
			//登录成功
			//创建Cookie    
	        Cookie nameCookie=new Cookie("name",URLEncoder.encode(username,"utf-8"));
	        Cookie pswCookie=new Cookie("psw",password);
	            
	        //设置Cookie的父路径    
	        nameCookie.setPath(request.getContextPath()+"/");    
	        pswCookie.setPath(request.getContextPath()+"/");    
	        logger.debug(remenber+"--------");
	        if(remenber!=null && remenber.equals("1")){//保存Cookie的时间长度，单位为秒
	        	nameCookie.setMaxAge(7*24*60*60);
	            pswCookie.setMaxAge(7*24*60*60);
	        }else{
	        	nameCookie.setMaxAge(0);    
	            pswCookie.setMaxAge(0); 
	        }
	        //加入Cookie到响应头    
	        response.addCookie(nameCookie);    
	        response.addCookie(pswCookie);    
		}
		return map;
	}
	
	@RequestMapping("logout")
	public String logout(){
		loginService.doLogout();
		return "1";
	}
	
}

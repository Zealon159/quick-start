package com.zealon.multi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zealon.multi.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 查询默认数据源
	 * @return
	 */
	@RequestMapping("master")
	public List<Map<String,Object>> selectAllByMasterDB(){
		return userService.selectAllByMasterDB();
	}
	
	/**
	 * 查询其它数据源
	 * @return
	 */
	@RequestMapping("other")
	public List<Map<String,Object>> selectAllByOtherDB(){
		return userService.selectAllByOtherDB();
	}
	
	/**
	 * 查询其它数据源
	 * @return
	 */
	@RequestMapping("all")
	public String selectAllDB(){
		return userService.selectAllDB();
	}
}

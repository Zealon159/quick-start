package cn.zealon.app.controller;

import cn.zealon.app.entity.SysDicType;
import cn.zealon.app.service.SysDicTypeService;
import cn.zealon.common.result.Result;
import cn.zealon.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("system/data-dic-type")
public class SysDicTypeController{
	
	@Autowired
	private SysDicTypeService sysDicTypeService;

	@ResponseBody
	@RequestMapping("/selectjson")
	public String getSelectJsonData(){
		return sysDicTypeService.findByCombox();
	}
	
	@ResponseBody
	@RequestMapping("/getSysDicTypeNavJson")
	public Map<String,Object> getSysDicTypeNavJson(){
		return sysDicTypeService.getSysDicTypeNav();
	}
}

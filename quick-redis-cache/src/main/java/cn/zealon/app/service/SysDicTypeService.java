package cn.zealon.app.service;

import cn.zealon.app.dao.SysDicTypeMapper;
import cn.zealon.app.entity.SysDicType;
import cn.zealon.common.result.Result;
import cn.zealon.common.result.ResultUtil;
import cn.zealon.common.utils.CommonUtil;
import cn.zealon.common.utils.PageConvertUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysDicTypeService")
public class SysDicTypeService {
	
	@Autowired
	private SysDicTypeMapper sysDicTypeMapper;

	public Result insert(SysDicType record) {
		sysDicTypeMapper.insert(record);
		return ResultUtil.success();
	}
	
	public Result update(SysDicType record) {
		sysDicTypeMapper.updateByPrimaryKey(record);
		return ResultUtil.success();
	}
	
	public Result delete(String id) {
		sysDicTypeMapper.deleteByPrimaryKey(id);
		return ResultUtil.success();
	}
	
	public String findByPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		Page<SysDicType> list = sysDicTypeMapper.selectAll();
		return PageConvertUtil.getGridJson(list);
	}
	
	/**
	 * 获取下拉json数据
	 * @return
	 */
	public String findByCombox(){
		String json = CommonUtil.getComboxJson(sysDicTypeMapper.selectAllByCombox());
		return json;
	}
	
	public List<SysDicType> selectAllByCondition(String key, Object value){
		Map<String, Object> params = new HashMap<String, Object>();
		if(CommonUtil.isNotBlank(key)){
			params.put(key, value);
		}
		return sysDicTypeMapper.selectAllByCondition(params);	
	}
	
	public Map<String, Object> getSysDicTypeNav(){
		Map<String, Object> group = new HashMap<String, Object>();
		List<SysDicType> projectList = selectAllByCondition("groupId", "project");
		List<SysDicType> customerList = selectAllByCondition("groupId", "customer");
		List<SysDicType> contractList = selectAllByCondition("groupId", "contract");
		group.put("code", 1);
		group.put("project", projectList);
		group.put("customer", customerList);
		group.put("contract", contractList);
		group.put("desc", "success");
		return group;
	}
	
}

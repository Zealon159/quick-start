package cn.zealon.app.service;

import cn.zealon.app.dao.SysDataDicMapper;
import cn.zealon.app.entity.SysDataDic;
import cn.zealon.common.domain.DataGridRequestDTO;
import cn.zealon.common.result.Result;
import cn.zealon.common.result.ResultUtil;
import cn.zealon.common.utils.CommonUtil;
import cn.zealon.common.utils.PageConvertUtil;
import cn.zealon.redis.RedisService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import cn.zealon.common.utils.UUIDUtil;

@Service("sysDataDicService")
public class SysDataDicService {
	
	@Autowired
	private RedisService redisService;

	@Autowired
	private SysDataDicMapper sysDataDicMapper;

	public Result insert(SysDataDic record) {
		Result rs = ResultUtil.fail();
		record.setId(UUIDUtil.getUUID());
		Date date = new Date();
		record.setCreated(date);
		record.setModified(date);
		if (sysDataDicMapper.insert(record)>0) {
			//删除缓存
			redisService.delete("data_dic_json:"+record.getDicType());
			rs = ResultUtil.success();
		} 
		return rs;
	}
	
	public Result update(SysDataDic record) {
		record.setModified(new Date());
		Result rs = ResultUtil.fail();
		if (sysDataDicMapper.updateByPrimaryKey(record)>0) {
			//删除缓存
			redisService.delete("data_dic_json:"+record.getDicType());
			rs = ResultUtil.success();
		} 
		return rs;
	}
	
	public Result delete(String id,String dicType) {
		Result rs = ResultUtil.fail();
		if (sysDataDicMapper.deleteByPrimaryKey(id)>0) {
			//删除缓存
			redisService.delete("data_dic_json:"+dicType);
			rs = ResultUtil.success();
		} 
		return rs;
	}
	
	public String findByPage(DataGridRequestDTO dgRequest) {
		PageHelper.startPage(dgRequest.getPage(), dgRequest.getLimit());
		Page<SysDataDic> list = sysDataDicMapper.selectAll(dgRequest.getParams());
		return PageConvertUtil.getGridJson(list);
	}
	
	public SysDataDic selectByPrimaryKey(String id){
		return sysDataDicMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * @desc: 根据id查找字典名称
	 * @param id
	 * @return
	 */
	public String getDataDicText(String id){
		String text = "";
		if(CommonUtil.isNotBlank(id)){
			SysDataDic sysDataDic = sysDataDicMapper.selectByPrimaryKey(id);
			if(sysDataDic!=null){
				text = sysDataDic.getDicName();
			}
		}
		return text;
	}
	
	/**
	 * @desc: 获取下拉json数据
	 * @return
	 */
	public String findByCombox(){
		String json = CommonUtil.getComboxJson(sysDataDicMapper.selectAllByCombox());
		return json;
	}
	
	/**
	 * @desc: 根据类型，获取下拉json数据
	 * @return
	 */
	@Cacheable(value="data_dic_json",cacheManager = "redisCacheManagerString",key="#p0")
	public String findByComboxByCode(String code){
		String json = CommonUtil.getComboxJson(sysDataDicMapper.selectAllByComboxByCode(code));
		return json;
	}
	
	public List<SysDataDic> findAllCode(String typeid){
		return sysDataDicMapper.findAllCode(typeid);
	}
}

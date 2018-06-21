package cn.zealon.common.domain;

import java.util.Map;

/**
 * 分页查询请求参数包装类
 * @author zealon
 * 
 */
public class DataGridRequestDTO {
	//当前页数
	private int page;
	//每页显示数
	private int limit;
	//查询条件
	private Map<String,Object> params;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
}

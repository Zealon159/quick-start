package cn.zealon.common.utils;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

/**
 * 分页转换工具类
 */
public class PageConvertUtil {
	
	/**
	 * 获取默认 grid json数据
	 * @param list
	 * @return
	 */
	public static String getGridJson(Page<?> list){
		return getLayuiJson(list);
	}
	
	/**
	 * 获取easyui grid json数据
	 * @param list
	 * @return
	 */
	public static String getEasyuiJson(Page<?> list){
		StringBuffer jsonData = new StringBuffer();
		jsonData.append("{");
		jsonData.append("\"total\":").append(list.getPages());
		jsonData.append(",\"rows\":").append(JSON.toJSONString(list));
		jsonData.append("}");
		return jsonData.toString();
	}
	
	/**
	 * 获取layui grid json数据
	 * @param list
	 * @return
	 */
	public static String getLayuiJson(Page<?> list){
		StringBuffer jsonData = new StringBuffer();
		jsonData.append("{");
		jsonData.append("\"code\":").append(0);
		jsonData.append(",\"msg\":\"\"");
		jsonData.append(",\"count\":").append(list.getTotal());
		jsonData.append(",\"data\":").append(JSON.toJSONStringWithDateFormat(list,"yyyy-MM-dd"));
		jsonData.append("}");
		return jsonData.toString();
	}
	
	/**
	 * 获取默认 grid json数据 日期格式化为时分秒
	 * @param list
	 * @return
	 */
	public static String getGridJson2(Page<?> list){
		return getLayuiJson2(list);
	}
	/**
	 * 日期格式化为时分秒
	 * @param list
	 * @return
	 */
	public static String getLayuiJson2(Page<?> list){
		StringBuffer jsonData = new StringBuffer();
		jsonData.append("{");
		jsonData.append("\"code\":").append(0);
		jsonData.append(",\"msg\":\"\"");
		jsonData.append(",\"count\":").append(list.getTotal());
		jsonData.append(",\"data\":").append(JSON.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss"));
		jsonData.append("}");
		return jsonData.toString();
	}
	
}

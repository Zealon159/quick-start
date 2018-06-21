package cn.zealon.common.utils;

import java.util.List;
import java.util.Map;

/**
 * 通用工具类
 * @auther: Zealon
 * @Date: 2018-06-21 10:57
 */
public class CommonUtil {
    /**
     * 获取select Json
     * @param list
     * @return
     */
    public static String getComboxJson(List<Map<String,Object>> list){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        int i = 0;
        for(Map<String,Object> obj : list){
            if(i>0){sb.append(",");}
            sb.append("{\"id\":\"").append(obj.get("id")).append("\",\"text\":\"").append(obj.get("text")).append("\"}");
            i++;
        }
        sb.append("]");
        return sb.toString();
    }

    private static boolean isEmpty(String string) {
        return (string == null) || (string.equals("null"))
                || (string.length() == 0);
    }

    private static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    public static boolean isBlank(String string) {
        return (isEmpty(string)) || (string.trim().length() == 0);
    }

    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }
}

package cn.zealon.mvc.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther: Zealon
 * @Date: 2018-07-12 14:49
 */
public class BaseController {
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {

        /**
         * 自动转换日期类型的字段格式

        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
         */

        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"),Date.class);
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd hh:mm:ss"),SimpleDateFormat.class);

        /**
         * 防止XSS攻击

        binder.registerCustomEditor(String.class, new StringEscapeEditor()); */
    }
}

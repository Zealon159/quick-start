package cn.zealon.mvc.controller;

import cn.zealon.mvc.entity.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;

/**
 * @Author: zealon
 * @Version: 1.0
 */
@Controller
public class DateFormatController {

    @ResponseBody
    @RequestMapping("format/get")
    public Object getUser(@RequestBody  UserBean userBean){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(simpleDateFormat.format(userBean.getCreated()));
        return userBean;
    }
}

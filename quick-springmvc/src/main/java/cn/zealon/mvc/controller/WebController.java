package cn.zealon.mvc.controller;

import cn.zealon.mvc.entity.UserBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: zealon
 * @Version: 1.0
 */
@Controller
public class WebController extends BaseController {

    @ResponseBody
    @GetMapping("index")
    public Object index(){
        List<Date> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(i,new Date());
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(name = "save",method = RequestMethod.POST,consumes = "application/json")
    public Object save(@RequestBody UserBean userBean){
        return userBean;
    }


    @ResponseBody
    @RequestMapping("test")
    public Object test(String userId,String userName){
        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        params.put("userName",userName);
        return params;
    }

    //@ResponseBody
    @GetMapping("get")
    public ResponseEntity<UserBean> get(){
        UserBean userBean = new UserBean();
        userBean.setUserId("libai");
        userBean.setUserName("李白");
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(userBean,status);
    }

    @ResponseBody
    @RequestMapping("datetime")
    public Object testDatetime(@RequestBody UserBean userBean){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        System.out.println(userBean.getCreated().toString());
        System.out.println(simpleDateFormat.format(userBean.getCreated()).toString());
        return userBean;
    }
}

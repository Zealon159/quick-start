package cn.zealon.mvc.controller;

import cn.zealon.mvc.entity.UserBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: zealon
 * @Version: 1.0
 */
@Controller
public class WebController {

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
    //@RequestMapping
    @RequestMapping(name = "save",method = RequestMethod.POST,consumes = "application/json")
    //@PostMapping(name = "save",consumes = "application/json",path = "save")
    public Object save(@RequestBody UserBean userBean){
        System.out.println(userBean.toString());
        return "ok";
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
}

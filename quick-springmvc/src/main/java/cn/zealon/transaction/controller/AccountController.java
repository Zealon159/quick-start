package cn.zealon.transaction.controller;

import cn.zealon.transaction.service.TbAccountService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther: Zealon
 * @Date: 2018-07-24 11:06
 */
@Controller
@RequestMapping("app/account")
public class AccountController {

    @Autowired
    private TbAccountService accountService;

    @ResponseBody
    @RequestMapping("/buy-car")
    public Object buyCar(String uid,String carName,int number){
        accountService.buyCar(uid,carName,number);
        return "ok";
    }

    public Object test(){
        AbstractApplicationContext applicationContext;
        return "ok";
    }
}

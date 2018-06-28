package cn.zealon.easyui.controller;

import cn.zealon.easyui.service.MenuServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther: Zealon
 */
@Controller
public class IndexController {

    @Autowired
    private MenuServcie menuServcie;

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("datebox")
    public String dateBox(){
        return "form/datebox";
    }

    @GetMapping("tab")
    public String tab(){
        return "form/tab";
    }

    @GetMapping("tab-multi")
    public String tabM(){
        return "form/tab-multi";
    }

    @ResponseBody
    @PostMapping("menu")
    public String getMenuJson(){
        return menuServcie.getMenu();
    }

    @ResponseBody
    @GetMapping("leftMenu")
    public String getLeftMenu(){
        return menuServcie.getLeftMenu();
    }
}

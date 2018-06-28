package cn.zealon.easyui.controller;

import cn.zealon.easyui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther: Zealon
 * @Date: 2018-06-27 15:55
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("listdata")
    public String selectAll(int rows,int page){
        return userService.findByPage(page,rows);
    }

    @GetMapping("datagrid")
    public String datagrid(){
        return "datagrid/datagrid";
    }

}

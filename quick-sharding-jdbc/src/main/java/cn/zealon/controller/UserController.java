package cn.zealon.controller;

import cn.zealon.entity.User;
import cn.zealon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther: Zealon
 * @Date: 2018-10-10 15:37
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/all")
    public List<User> selectAll(){
        return service.selectAll();
    }

    @PostMapping("/add")
    public boolean add(User user){
        return service.add(user)>0;
    }

}

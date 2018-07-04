package cn.zealon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther: Zealon
 * @Date: 2018-07-04 17:28
 */
@Controller
public class PageController {
    @GetMapping("login")
    public String login(){
        return "login";
    }
}

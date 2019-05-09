package cn.zealon.controller;

import cn.zealon.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: tangyl
 * @since: 2019/5/9
 * @version: 1.0
 */
@Controller
public class HomePageController {

    @Autowired
    private MenuService menuService;

    @PostMapping("menu/getTreeData")
    public @ResponseBody String getTreeData(){
        return menuService.getHomeMenu();
    }

    @GetMapping("index")
    public String index(){
        return "index";
    }
}

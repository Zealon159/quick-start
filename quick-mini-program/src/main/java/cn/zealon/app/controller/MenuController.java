package cn.zealon.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: Zealon
 * @Date: 2018-07-31 16:02
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @RequestMapping("/list")
    public Object getMenu(String name){
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("url","url"+i);
            map.put("name","name:"+name+i);
            list.add(map);
        }
        return list;
    }
}

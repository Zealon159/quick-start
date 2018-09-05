package cn.zealon.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther: Zealon
 * @Date: 2018-09-05 16:44
 */
@Controller
public class WebController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }
}

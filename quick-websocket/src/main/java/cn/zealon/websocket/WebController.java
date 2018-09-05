package cn.zealon.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @auther: Zealon
 * @Date: 2018-09-05 16:44
 */
@Controller
public class WebController {

    @Autowired
    private WebSocketTest webSocketTest;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("send/{uid}/{msg}")
    public String sendMsg(@PathVariable("uid") String uid,@PathVariable("msg") String msg){

        webSocketTest.sendToUser(msg+"|"+uid);
        return "ok?";
    }
}

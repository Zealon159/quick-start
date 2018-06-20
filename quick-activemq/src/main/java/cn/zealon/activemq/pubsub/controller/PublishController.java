package cn.zealon.activemq.pubsub.controller;

import cn.zealon.activemq.pubsub.service.PublishService;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pub")
public class PublishController {

    @Autowired
    private PublishService publishService;

    @RequestMapping("add")
    public Object publish(String message){
        publishService.publish(message);
        return "ok";
    }
}

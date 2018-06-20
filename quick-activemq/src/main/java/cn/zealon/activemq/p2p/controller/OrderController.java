package cn.zealon.activemq.p2p.controller;

import cn.zealon.activemq.p2p.service.OrderConsumerService;
import cn.zealon.activemq.p2p.service.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderProducerService producerService;

    @PostMapping("add")
    public Object addOrder(String queue,String message){
        producerService.sendTextMessage(queue,message);
        return "ok";
    }
}

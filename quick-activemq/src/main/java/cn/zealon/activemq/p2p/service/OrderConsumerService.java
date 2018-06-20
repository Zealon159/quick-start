package cn.zealon.activemq.p2p.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 订单消费服务
 */
@Component
public class OrderConsumerService {

    @JmsListener(destination = "order.queue")
    public void receiveQueue(String message){
        System.out.println("消费消息："+message);
    }
}

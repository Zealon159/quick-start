package cn.zealon.activemq.p2p.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Queue;

@Service
public class OrderProducerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    /**
     * 发送消息
     * @param message 消息内容
     *
     */
    public void sendTextMessage(String message){
        jmsMessagingTemplate.convertAndSend(this.queue,message);
    }

    /**
     * 发送消息
     * @param destinationName 发送的队列
     * @param message 消息内容
     */
    public void sendTextMessage(String destinationName,String message){
        //队列
        Destination destination = new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}

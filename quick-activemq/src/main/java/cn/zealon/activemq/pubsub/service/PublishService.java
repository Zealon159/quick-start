package cn.zealon.activemq.pubsub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import javax.jms.Topic;

/**
 * 发布者服务类
 */
@Service
public class PublishService {

    @Autowired
    private Topic topic;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void publish(String message){
        jmsMessagingTemplate.convertAndSend(topic,message);
    }
}

package cn.zealon.activemq.pubsub.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class SubscribeA {

    @JmsListener(destination = "order.topic",containerFactory = "topicFactory")
    public void receive(final TextMessage message){
        String text = null;
        try {
            text = message.getText();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+
                " SubscribeA订阅消息："+text);
    }
}

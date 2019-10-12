package cn.zealon.rabbit.pubsub;

import cn.zealon.rabbit.config.AMQPConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息订阅者
 * @author: tangyl
 * @since: 2019/10/12
 */
@Component
public class Subscribe {

    @RabbitListener(queues = AMQPConfig.MD_BOOK_STATUS_QUEUE, concurrency = "1-5")
    public void processMessage(Message message) {
        String body = new String(message.getBody());
        System.out.println(body);
    }
}

package cn.zealon.rabbitsub.sub;

import cn.zealon.rabbitsub.config.AMQPConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息订阅者
 * @author: tangyl
 * @since: 2019/10/12
 */
@Component
public class Subscribe {

    @RabbitListener(queues = AMQPConfig.BOOK_STATUS_QUEUE, concurrency = "1-5" )
    public void processMessage(Message message,Channel channel) {
        String body = new String(message.getBody());

        // 确认消费
        Long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            System.out.println("sub2:"+body+"; deliveryTag:"+deliveryTag);
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

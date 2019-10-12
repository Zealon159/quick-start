package cn.zealon.rabbit.pubsub;

import cn.zealon.rabbit.config.AMQPConfig;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息发布者
 * @author: tangyl
 * @since: 2019/10/12
 */
@RestController
@RequestMapping("pub")
public class Publish {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @RequestMapping("/sendMsg")
    public String sendMsg(String msg){

        // 发消息
        Map<String , Object> map = new HashMap<>();
        map.put( "msg" , msg );
        map.put( "data" , Arrays.asList("中文" , 123 , true) );
        rabbitTemplate.convertAndSend(AMQPConfig.FANOUT_EXCHANGE,"",map);
        return "ok";
    }
}

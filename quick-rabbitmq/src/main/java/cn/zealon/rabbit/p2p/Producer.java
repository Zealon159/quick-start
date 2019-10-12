package cn.zealon.rabbit.p2p;

import cn.zealon.rabbit.config.AMQPConfig;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息生产者
 * @author: tangyl
 * @since: 2019/10/11
 */
@RestController
@RequestMapping("p2p")
public class Producer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @InitBinder
    public void init(){
        System.out.println("init...");
    }

    @RequestMapping("/sendMsg")
    public String sendMsg(String msg){
        // 命名
        String destination = AMQPConfig.BOOK_SHELF_QUEUE;
        String exchange = "exchange.direct";
        String routingKey = "direct.queue";

        // 创建交换器
        DirectExchange directExchange = new DirectExchange(exchange);
        amqpAdmin.declareExchange( directExchange );

        // 创建绑定器
        Binding binding = new Binding(destination, Binding.DestinationType.QUEUE,
                exchange,routingKey,null);
        amqpAdmin.declareBinding(binding);

        // 发消息
        Map<String , Object> map = new HashMap<>();
        map.put( "msg" , msg );
        map.put( "data" , Arrays.asList("helloworld" , 123 , true) );
        rabbitTemplate.convertAndSend(exchange,routingKey,map);
        return "ok";
    }
}

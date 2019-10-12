package cn.zealon.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: tangyl
 * @since: 2019/10/12
 */
@Configuration
public class AMQPConfig {

    /** p2p */
    public static final String BOOK_SHELF_QUEUE = "queue.bookshelf";
    /** 定义队列 */
    @Bean
    public Queue bookshelfQueue(){
        return new Queue(BOOK_SHELF_QUEUE,true);
    }


    /** pub/sub */
    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    public static final String MD_BOOK_STATUS_QUEUE = "queue.miandian.bookstatus";
    public static final String ZWSC_BOOK_STATUS_QUEUE = "queue.zwsc.bookstatus";

    @Bean
    public Queue mdBookStatusQueue(){
        return new Queue(MD_BOOK_STATUS_QUEUE,true);
    }
    @Bean
    public Queue zwscBookStatusQueue(){
        return new Queue(ZWSC_BOOK_STATUS_QUEUE,true);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        //配置广播路由器
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeA(@Qualifier("mdBookStatusQueue") Queue mdMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(mdMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(@Qualifier("zwscBookStatusQueue") Queue zwscMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(zwscMessage).to(fanoutExchange);
    }



    /** 消息转换器 */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

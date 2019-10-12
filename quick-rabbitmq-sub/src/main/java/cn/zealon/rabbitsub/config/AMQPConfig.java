package cn.zealon.rabbitsub.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: tangyl
 * @since: 2019/10/12
 */
@Configuration
public class AMQPConfig {

    public static final String BOOK_STATUS_QUEUE = "queue.zwsc.bookstatus";

    /** 定义队列 */
    @Bean
    public Queue bookStatusQueue(){
        return new Queue(BOOK_STATUS_QUEUE,true);
    }

    /** 消息转换器 */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

package cn.zealon.activemq.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import javax.jms.ConnectionFactory;
import javax.jms.Topic;

/**
 * ActiveMQ 配置
 * @auther: Zealon
 * @Date: 2018-01-20 16:41
 */
@Configuration
@EnableJms
public class ActiveMQConfig {

    // 参考 https://blog.csdn.net/zhaoyachao123/article/details/78365003

    @Bean
    public Topic topic(){
        return new ActiveMQTopic("order.topic");
    }

    //消息监听器连接工厂
    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        //JMS监听者并发线程数范围
        factory.setConcurrency("1-10");
        //重连间隔时间
        factory.setRecoveryInterval(1000L);
        //应答模式
        factory.setSessionAcknowledgeMode(2);

        configurer.configure(factory, connectionFactory);

        return factory;
    }

    //订阅监听器连接工厂
    @Bean
    public JmsListenerContainerFactory<?> topicFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        //JMS监听者并发线程数范围
        factory.setConcurrency("1-1");
        //重连间隔时间
        factory.setRecoveryInterval(1000L);
        //支持发布/订阅
        factory.setPubSubDomain(true);

        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    /*@Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }*/
}

package cn.zealon;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;


@SpringBootApplication
@EnableJms
public class Application {

    //交给spring管理，方便注入使用
    @Bean
    public Queue queue(){
        return new ActiveMQQueue("common.queue");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

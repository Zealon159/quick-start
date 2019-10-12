package cn.zealon.rabbitsub;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class QuickRabbitmqSubApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickRabbitmqSubApplication.class, args);
    }

}

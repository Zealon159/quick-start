package cn.zealon.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class QuickRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickRabbitmqApplication.class, args);
	}

}

package cn.zealon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableWebSocket
@SpringBootApplication
public class QuickWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickWebsocketApplication.class, args);
    }
}

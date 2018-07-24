package cn.zealon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class QuickSpringmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickSpringmvcApplication.class, args);
    }
}

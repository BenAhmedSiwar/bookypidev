package tn.esprit.spring.spring11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class Spring11Application {

    public static void main(String[] args) {

        SpringApplication.run(Spring11Application.class, args);
    }

}

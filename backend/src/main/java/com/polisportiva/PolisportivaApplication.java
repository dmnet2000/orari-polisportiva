package com.polisportiva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.polisportiva"})
public class PolisportivaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolisportivaApplication.class, args);
    }

}

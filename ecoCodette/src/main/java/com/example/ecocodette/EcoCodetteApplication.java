/*
- starts the app and connects everything.
 */
package com.example.ecocodette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication()
public class EcoCodetteApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcoCodetteApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(RequestMappingHandlerMapping mapping) {
        return args -> mapping.getHandlerMethods().forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });
    }
}

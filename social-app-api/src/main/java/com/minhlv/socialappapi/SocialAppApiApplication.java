package com.minhlv.socialappapi;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@RequiredArgsConstructor
@ComponentScan({ "com.minhlv.socialappapi.*" })
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableJpaAuditing
public class SocialAppApiApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SocialAppApiApplication.class, args);
    }

}

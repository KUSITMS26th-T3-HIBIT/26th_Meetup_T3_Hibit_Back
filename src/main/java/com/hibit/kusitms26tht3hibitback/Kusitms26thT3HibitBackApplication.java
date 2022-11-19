package com.hibit.kusitms26tht3hibitback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;

import javax.persistence.Entity;

@EnableJpaAuditing
@SpringBootApplication(exclude = ContextRegionProviderAutoConfiguration.class)
public class Kusitms26thT3HibitBackApplication {
    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    public static void main(String[] args) {
        SpringApplication.run(Kusitms26thT3HibitBackApplication.class, args);
    }

}

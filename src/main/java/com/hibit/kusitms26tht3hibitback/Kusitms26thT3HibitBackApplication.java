package com.hibit.kusitms26tht3hibitback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Kusitms26thT3HibitBackApplication {
    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    public static void main(String[] args) {
        SpringApplication.run(Kusitms26thT3HibitBackApplication.class, args);
    }

}

package com.solr.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.solr.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {

        //1:SpringApplication.run(DemoApplication.class, args);
        //2
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.addInitializers(new DesignInitApplication());
        application.run(args);
    }

}

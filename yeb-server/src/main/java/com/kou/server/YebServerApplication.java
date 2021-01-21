package com.kou.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author JIAJUN KOU
 */
@SpringBootApplication
@MapperScan("com.kou.server.mapper")
public class YebServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(YebServerApplication.class,args);
    }

}

package com.kou.server;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author JIAJUN KOU
 */
@SpringBootApplication
@MapperScan("com.kou.server.mapper")
@EnableScheduling
public class YebServerApplication {
    private static final Logger log = LoggerFactory.getLogger(YebServerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(YebServerApplication.class,args);
    }

}

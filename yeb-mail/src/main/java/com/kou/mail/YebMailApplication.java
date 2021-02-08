package com.kou.mail;


import com.kou.server.pojo.MailConstants;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author JIAJUN KOU
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class YebMailApplication {
    private static final Logger log = LoggerFactory.getLogger(YebMailApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(YebMailApplication.class,args);
    }

    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }


}

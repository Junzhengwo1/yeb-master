package com.kou.server.config.rabbitmq;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kou.server.pojo.MailConstants;
import com.kou.server.pojo.MailLog;
import com.kou.server.service.IMailLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JIAJUN KOU
 */
@Configuration
public class RabbitMqConfig {

    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMqConfig.class);

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private IMailLogService mailLogService;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        //开始消息回调
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            String msgId = data.getId();
            if(ack){
                LOGGER.info("消息发送成功",msgId);
                mailLogService.update(new UpdateWrapper<MailLog>().set("status",1).eq("msgId",msgId));
            }
            else {
                LOGGER.error("消息发送失败",msgId);
            }
        });

        rabbitTemplate.setReturnCallback((msg, repCode, repText, exchange, routingkey) -> {
                LOGGER.error("消息发送到队列失败",msg.getBody());
        });
        return rabbitTemplate;
    }






    /**
     * 队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }

    /**
     * 交换机
     *
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }

    /**
     * 绑定
     * @return
     */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }


}

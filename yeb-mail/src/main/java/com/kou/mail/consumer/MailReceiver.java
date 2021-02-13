package com.kou.mail.consumer;

import com.kou.server.pojo.Employee;
import com.kou.server.pojo.MailConstants;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

/**
 * @author JIAJUN KOU
 */
@Component
public class MailReceiver {

    private static final Logger LOGGER= LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message message, Channel channel){
        Employee employee = (Employee)message.getPayload();
        MessageHeaders headers = message.getHeaders();
        //消息序号
        long tag =(long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId =(String) headers.get("spring_returned_message_correlation");
        //将接受的消息存入redis中
        HashOperations hashOperations = redisTemplate.opsForHash();

        try {
            if(hashOperations.entries("mail_log").containsKey(msgId)){
                LOGGER.error("消息已经被消费=========={}",msgId);
                //手动确认消息 tag:消息序号 是否多条确认
                channel.basicAck(tag,false);
                return;
            }
            //接受到消息队列的消息后，准备开始发送消息了
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            //发件人
            helper.setFrom(mailProperties.getUsername());
            //收件人
            helper.setTo(employee.getEmail());
            //邮件主题
            helper.setSubject("员工入职邮件");
            //日期
            helper.setSentDate(new Date());
            //邮件内容
            Context context=new Context();
            context.setVariable("name",employee.getName());
            context.setVariable("posName",employee.getPosition().getName());
            context.setVariable("joblevelName",employee.getJoblevel().getName());
            context.setVariable("departmentName",employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
            //发送
            javaMailSender.send(mimeMessage);
            LOGGER.info("邮件发送成功");
            //将消息id存入redis中
            hashOperations.put("mail_log",msgId,"ok");
            channel.basicAck(tag,false);
        } catch (Exception e) {
            try {
                channel.basicNack(tag,false,true);
            } catch (IOException ex) {
                LOGGER.error("邮件发送失败======={}",e.getMessage());
            }
            LOGGER.error("邮件发送失败======={}",e.getMessage());
        }

    }
}

package com.kou.mail.consumer;

import com.kou.server.pojo.Employee;
import com.kou.server.pojo.MailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Employee employee){
        //接受到消息队列的消息后，准备开始发送消息了
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            //发件人
            helper.setFrom(mailProperties.getUsername());
            //收件人
            helper.setTo(employee.getEmail());
            //邮件主题
            helper.setSubject("入职欢迎邮件");
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

        } catch (MessagingException e) {
            LOGGER.error("邮件发送失败=======");
        }


    }
}

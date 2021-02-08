package com.kou.server.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kou.server.pojo.Employee;
import com.kou.server.pojo.MailConstants;
import com.kou.server.pojo.MailLog;
import com.kou.server.service.IEmployeeService;
import com.kou.server.service.IMailLogService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author JIAJUN KOU
 * 邮件定时任务
 */
@Component
public class MailTask {

    @Autowired
    private IMailLogService mailLogService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 邮件发送定时任务
     * 10秒一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void mailTask(){
        List<MailLog> list = mailLogService.list(new QueryWrapper<MailLog>().eq("status", 0).lt("tryTime", LocalDateTime.now()));
        list.forEach(mailLog -> {
            //如果重试次数超过3次，更新状态为投递失败
            if(3<=mailLog.getCount()){
                mailLogService.update(new UpdateWrapper<MailLog>().set("status",2).eq("msgId",mailLog.getMsgId()));
            }
            mailLogService.update(new UpdateWrapper<MailLog>().set("count",mailLog.getCount()+1).set("updateTime",LocalDateTime.now()).set("tryTime",LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT)));

            Employee employee = employeeService.getEmployeeToExcel(mailLog.getEid()).get(0);
            //发送
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,MailConstants.MAIL_ROUTING_KEY_NAME,employee,new CorrelationData(mailLog.getMsgId()));


        });


    }

}

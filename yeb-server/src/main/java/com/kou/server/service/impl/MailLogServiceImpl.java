package com.kou.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.server.mapper.MailLogMapper;
import com.kou.server.pojo.MailLog;
import com.kou.server.service.IMailLogService;
import org.springframework.stereotype.Service;

/**
 * @author JIAJUN KOU
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {
}

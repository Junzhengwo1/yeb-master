package com.kou.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.server.mapper.PoliticsStatusMapper;
import com.kou.server.pojo.PoliticsStatus;
import com.kou.server.service.IPoliticsStatusService;
import org.springframework.stereotype.Service;

/**
 * @author JIAJUN KOU
 */
@Service
public class PoliticsStatusServiceImpl extends ServiceImpl<PoliticsStatusMapper, PoliticsStatus> implements IPoliticsStatusService {
}

package com.kou.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.server.mapper.NationMapper;
import com.kou.server.pojo.Nation;
import com.kou.server.service.INationService;
import org.springframework.stereotype.Service;

/**
 * @author JIAJUN KOU
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {
}

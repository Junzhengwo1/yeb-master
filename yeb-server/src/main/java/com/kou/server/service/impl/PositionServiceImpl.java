package com.kou.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.server.mapper.PositionMapper;
import com.kou.server.pojo.Position;
import com.kou.server.service.IPositionService;
import org.springframework.stereotype.Service;

/**
 * @author JIAJUN KOU
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}

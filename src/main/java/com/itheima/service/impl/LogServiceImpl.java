package com.itheima.service.impl;

import com.itheima.mapper.LogMapper;
import com.itheima.pojo.Log;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(Log log) {
        logMapper.insert(log);
    }
}

package com.itheima.service.impl;

import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Log;
import com.itheima.service.DeptService;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper  deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private LogService logService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    /**
     * 1.解散部门：删除部门、删除部门下的员工
     * 2.记录日志到数据库表中
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    /*
    @Transactional
    位置：业务（service）层的方法上、类上、接口上
    作用：将当前方法交给spring进行事务管理.方法执行前，开启事务；成功执行完毕，提交事务；出现异常，回滚事务
    (1)默认情况下，只有出现 RuntimeException 才回滚异常。
    rollbackFor属性用于控制出现何种异常类型，回滚事务。
    (2)propagation属性:
        事务传播行为：指的就是当一个事务方法被另一个事务方法调用时，这个事务方法应该如何进行事务控制.
       属性值(常用的)   含义
       REQUIRED       【默认值】需要事务，有则加入，无则创建新事务
       REQUIRES_NEW   需要新事务，无论有无，总是创建新事务
     */
    @Override
    public void deleteById(Integer id) {
        try {
            deptMapper.deleteById(id);//删除部门
//            int i = 1/0;
            empMapper.deleteByDeptId(id);//删除部门下的员工
        } finally {
            Log log = new Log();
            log.setCreateTime(LocalDateTime.now());
            log.setDescription("执行了解散部门操作，此次操作的部门id为："+id);
            logService.insert(log);//记录操作日志

        }


//        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}

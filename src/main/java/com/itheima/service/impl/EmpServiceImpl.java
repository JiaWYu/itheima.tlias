package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//
//        if(page == null) page = 1;
//        if(pageSize == null) pageSize = 10;
//
//        Long total = empMapper.count();
//
//        List<Emp> empList = empMapper.page((page-1)*pageSize,pageSize);
//
//        PageBean pageBean = new PageBean(total,empList);
//
//        return pageBean;
//
//    }
@Override
public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {

//    if(page == null) page = 1;
//    if(pageSize == null) pageSize = 10;


    //PageHelper
//    1.设置分页参数
    PageHelper.startPage(page, pageSize);

//    2.执行查询
    List<Emp> empList = empMapper.list(name, gender, begin, end);
    Page<Emp> p = (Page<Emp>) empList;

    PageBean pageBean = new PageBean(p.getTotal(),p.getResult());

    return pageBean;

}

    @Override
    public void delete(List<Integer> ids) {

        empMapper.delete(ids);

    }

    @Override
    public void save(Emp emp) {

        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {

        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }


}

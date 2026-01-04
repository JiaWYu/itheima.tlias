package com.itheima.service;


import com.itheima.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {

    public List<Dept> list();
    public void deleteById(Integer id);

    public void add(Dept dept);

    public Dept getById(Integer id);

    public void update(Dept dept);
}

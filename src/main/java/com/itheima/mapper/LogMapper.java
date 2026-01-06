package com.itheima.mapper;


import com.itheima.pojo.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {

    @Insert("insert into tb_log (create_time, description) VALUES (#{createTime},#{description})")
    void insert(Log log);
}

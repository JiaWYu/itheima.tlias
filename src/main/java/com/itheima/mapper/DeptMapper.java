package com.itheima.mapper;


import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from tb_dept")
    public List<Dept> list();

    @Delete("delete from tb_dept where id = #{id}")
    public void deleteById(Integer id);

    @Insert("insert into tb_dept (name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    public void insert(Dept dept);

    @Select("select id,name,create_time,update_time from tb_dept where id = #{id}")
    public Dept getById(Integer id);

    @Update("update tb_dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    public void update(Dept dept);
}

package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from tb_emp")
//    public Long count();
//
//    @Select("select * from tb_emp limit #{start},#{pageSize}")
//    public List<Emp> page(@Param("start") Integer start,@Param("pageSize") Integer pageSize);

    //PageHelper
//    @Select("select * from tb_emp")
    public List<Emp> list(@Param("name") String name,@Param("gender") Short gender,
                          @Param("begin") LocalDate begin,@Param("end") LocalDate end);

    void delete(@Param("ids") List<Integer> ids);

    @Insert("insert into tb_emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "VALUES (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    @Select("select * from tb_emp where id = #{id}")
    Emp getById(Integer id);

    void update(Emp emp);
}

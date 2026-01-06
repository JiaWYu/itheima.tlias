package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 条件分页查询员工信息
     * @param page
     * @param pageSize
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        log.info("条件分页查询员工信息,参数:{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        return Result.success(empService.page(page, pageSize, name, gender, begin, end));

    }

    /**
     * 批量删除员工
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除员工：{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 新增员工
     * @param emp
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {

        log.info("根据ID查询员工信息：{}",id);
        Emp emp = empService.getById(id);

        return Result.success(emp);
    }

    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Emp emp) {

        log.info("修改员工信息：{}",emp);
        empService.update(emp);

        return Result.success();
    }

}

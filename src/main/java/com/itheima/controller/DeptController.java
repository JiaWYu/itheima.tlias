package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@Slf4j//日志
@RequestMapping("/depts")//抽取共同路径
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;



//    private static Logger logger = LoggerFactory.getLogger(DeptController.class);
//    @RequestMapping(value = "/depts", method = RequestMethod.GET)

    /**
     * 查询全部部门
     * @return
     */
//    @GetMapping("/depts")
    @GetMapping
    public Result list(){

        log.info("查询全部部门数据");

        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
//    @DeleteMapping("/depts/{id}")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){

        log.info("根据id删除部门：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }


    /**
     * 新增部门
     * @param dept
     * @return
     */
//    @PostMapping("/depts")
    @PostMapping
    public Result add(@RequestBody Dept dept){

        log.info("添加部门："+dept.getName());

        deptService.add(dept);

        return Result.success();

    }


    /**
     * 根据ID查询部门
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){

        log.info("根据ID查询部门：{}",id);

        Dept dept = deptService.getById(id);

        return Result.success(dept);

    }


    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Dept dept){

        log.info("修改部门信息，ID：{}",dept.getId());

        deptService.update(dept);

        return Result.success();

    }

}

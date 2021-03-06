package com.guli.edu.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.exception.GuliException;
import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.query.TeacherQuery;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create with IntelliJ IDEA。
 * User : Lyhang
 * Data : 2019-02-23
 * Time : 16:40
 **/
@Api(description = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
@CrossOrigin
public class TeacherAdminController {
    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "讲师列表")
    @GetMapping
    public R getAll(){

        List<Teacher> list = teacherService.list(null);

        return R.ok().data("items",list);
    }
    @ApiOperation(value = "根据Id删除讲师")
    @DeleteMapping("{id}")
    public R deleteById(
            @ApiParam(name = "id",value = "讲师Id",required = true)
            @PathVariable("id") String id){

        Teacher byId = teacherService.getById(id);

        if(byId==null){

            R.setResult(ResultCodeEnum.PARAM_ERROR);

        }

        boolean b = teacherService.removeById(id);


        return R.ok().success(b);
    }

    /**
     * 教师查询分页
     */
    @ApiOperation(value = "教师分页列表")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "limit",value = "每页记录数",required = true)
            @PathVariable Long limit,
            @ApiParam(name ="page",value = "当前页码",required = true)
            @PathVariable Long page,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            TeacherQuery teacherQuery){

        if(page <= 0 || limit <= 0){
            //throw new GuliException(21003, "参数不正确1");
            throw new GuliException(ResultCodeEnum.PARAM_ERROR);
        }

        Page<Teacher> pageParam = new Page<>(page,limit);
        teacherService.pageQuery(pageParam,teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return R.ok().data("records",records).data("total",total);

    }




    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){

        teacherService.save(teacher);
        return R.ok();
    }


    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){

        teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok();
    }



}

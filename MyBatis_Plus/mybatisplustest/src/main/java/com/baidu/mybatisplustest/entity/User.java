package com.baidu.mybatisplustest.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Create with IntelliJ IDEA。
 * User : Lyhang
 * Data : 2019-02-22
 * Time : 14:49
 **/
@Data
public class User {
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;


    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date creatTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;




}

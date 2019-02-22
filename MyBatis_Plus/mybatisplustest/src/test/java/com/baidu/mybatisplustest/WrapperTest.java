package com.baidu.mybatisplustest;

import com.baidu.mybatisplustest.entity.User;
import com.baidu.mybatisplustest.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDEA。
 * User : Lyhang
 * Data : 2019-02-22
 * Time : 19:01
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;


    /**
     * ge大于等于
     * le小于等于
     * lt小于
     * gt大于
     * eq等于
     * ne不等于
     */
    /**
     * UPDATE user SET deleted=1
     * WHERE deleted=0
     * AND name IS NULL AND age >= ? AND email IS NOT NULL
     */
    @Test
    public void testDelete(){
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.
                isNull("name")
                .ge("age", 12)
                .isNotNull("email");
        int result = userMapper.delete(objectQueryWrapper);
        System.out.println("delete return count = " + result);


    }

    /**
     * SELECT id,name,age,email,create_time,update_time,deleted,version
     * FROM user
     * WHERE deleted=0 AND name = ?
     */
    @Test
    public void testSelectOne() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Tom");

        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    /**
     * SELECT id,name,age,email,create_time,update_time,deleted,version
     FROM user WHERE deleted=0 AND name = ? AND id = ? AND age = ?
     */
    @Test
    public void testSelectAll() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name","Tom");
        map.put("age",24);
        queryWrapper.allEq(map);

        List<User> user = userMapper.selectList(queryWrapper);
        user.forEach(System.out::print);
    }

    /**
     * SELECT id,name,age,email,create_time,update_time,deleted,version
     FROM user WHERE deleted=0 AND name NOT LIKE ? AND email LIKE ?
     */

    @Test
    public void testSelectMaps() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .notLike("name", "e")
                .likeRight("email", "t");

        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);//返回值是Map列表
        maps.forEach(System.out::println);
    }

    /**
     * SELECT id,name,age,email,create_time,update_time,deleted,version
     FROM user WHERE deleted=0 AND id IN (select id from user where id < 3)
     */
    @Test
    public void testSelectObjs() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.in("id", 1, 2, 3);
        queryWrapper.inSql("id", "select id from user where id < 3");

        List<Object> objects = userMapper.selectObjs(queryWrapper);//返回值是Object列表
        objects.forEach(System.out::println);
    }

    /**
     * UPDATE user SET name=?, age=?, update_time=?
     * WHERE deleted=0 AND name LIKE ? OR age BETWEEN ? AND ?
     */
    @Test
    public void testUpdate1() {

        //修改值
        User user = new User();
        user.setAge(99);
        user.setName("Andy");

        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .or()
                .between("age", 20, 30);

        int result = userMapper.update(user, userUpdateWrapper);

        System.out.println(result);
    }
    /**UPDATE user SET name=?, age=?, update_time=?
        WHERE deleted=0 AND name LIKE ?
       OR ( name = ? AND age <> ? )*/
    @Test
    public void testUpdate2() {


        //修改值
        User user = new User();
        user.setAge(99);
        user.setName("Andy");

        //修改条件

        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .or(i -> i.eq("name", "李白").ne("age", 20));

        int result = userMapper.update(user, userUpdateWrapper);

        System.out.println(result);
    }

    /**
     * SELECT id,name,age,email,create_time,update_time,deleted,version
     FROM user WHERE deleted=0 ORDER BY id DESC
     */
    @Test
    public void testSelectListOrderBy() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * SELECT id,name,age,email,create_time,update_time,deleted,version
        FROM user WHERE deleted=0 limit 1
     */
    @Test
    public void testSelectListLast() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("limit 1");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * SELECT id,name,age FROM user WHERE deleted=0
     */
    @Test
    public void testSelectListColumn() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name", "age");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    /**
     * UPDATE user SET age=?, update_time=?, name=?, email = '123@qq.com'
     * WHERE deleted=0 AND name LIKE ?
     */
    @Test
    public void testUpdateSet() {

        //修改值
        User user = new User();
        user.setAge(99);

        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .set("name", "老李头")//除了可以查询还可以使用set设置修改的字段
                .setSql(" email = '123@qq.com'");//可以有子查询

        int result = userMapper.update(user, userUpdateWrapper);
    }

}

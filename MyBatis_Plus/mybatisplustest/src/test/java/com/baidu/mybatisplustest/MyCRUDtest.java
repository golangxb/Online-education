package com.baidu.mybatisplustest;

import com.baidu.mybatisplustest.entity.User;
import com.baidu.mybatisplustest.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDEA。
 * User : Lyhang
 * Data : 2019-02-22
 * Time : 15:56
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyCRUDtest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("张三呵呵啊哈哈");
        user.setAge(18);
        user.setEmail("1111@qq.com");

        int insert = userMapper.insert(user);

        System.out.println(insert);
        System.out.println(user);
    }
    @Test
    public void testUpdate(){
        //查询
        User user = userMapper.selectById(1L);
        //修改数据
        user.setName("Helen Yao");
        user.setEmail("helen@qq.com");
        //执行更新
        userMapper.updateById(user);
    }

    @Test
    public void testOptimisticLockerFail() {

        //查询
        User user = userMapper.selectById(1L);
        //修改数据
        user.setName("Helen Yao1");
        user.setEmail("helen@qq.com1");

        //模拟取出数据后，数据库中version实际数据比取出的值大，即已被其它线程修改并更新了version
        user.setVersion(user.getVersion() - 1);

        //执行更新
        userMapper.updateById(user);
    }
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::print);
    }
    
    
    @Test
    public void testSelect(){
        Map<String,Object> map =new HashMap<>();
        map.put("name","Jack");

        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::print);

    }

    @Test
    public void testSelectPage() {

        Page<User> page = new Page<>(2,5);
        userMapper.selectPage(page, null);


        page.getRecords().forEach(System.out::println);
        //当前页码
        System.out.println(page.getCurrent());
        //总页数
        System.out.println(page.getPages());
        //每页显示多少
        System.out.println(page.getSize());
        //总条数
        System.out.println(page.getTotal());
        //是否有下一页
        System.out.println(page.hasNext());
        //是否有上一页
        System.out.println(page.hasPrevious());
    }

    @Test
    public void testPageMap(){
        Page<User> page = new Page<>(1,5);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page,null);
        mapIPage.getRecords().forEach(System.out::print);

        System.out.println(mapIPage.getCurrent());
        System.out.println(mapIPage.getPages());
        System.out.println(mapIPage.getSize());
        System.out.println(mapIPage.getTotal());
    }

    @Test
    public void testdelect(){
        int i = userMapper.deleteById(1L);
        System.out.println(i);
    }
    @Test
    public void testSelectAll(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}

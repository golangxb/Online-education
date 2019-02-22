package com.baidu.mybatisplustest;

import com.baidu.mybatisplustest.entity.User;
import com.baidu.mybatisplustest.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplustestApplicationTests {
	@Autowired
	UserMapper userMapper;

	@Test
	public void contextLoads() {

		List<User> users = userMapper.selectList(null);
		users.forEach(System.out::print);

	}

}

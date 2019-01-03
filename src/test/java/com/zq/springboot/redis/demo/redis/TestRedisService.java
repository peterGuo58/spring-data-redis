package com.zq.springboot.redis.demo.redis;

import com.zq.springboot.redis.demo.domain.User;
import com.zq.springboot.redis.demo.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisService {
	@Autowired
	private RedisService redisService;

    @Test
    public void testString() throws Exception {
        redisService.set("neo", "ityouknow");
        Assert.assertEquals("ityouknow", redisService.get("neo"));
    }
    
    @Test
    public void testObj() throws Exception {
        User user=new User("ityouknow@126.com", "smile", "youknow", "know","2020");
        redisService.set("user",user);
        User userR=(User) redisService.get("user");
        System.out.println("User from redis:------------>"+userR.toString());
    }


}
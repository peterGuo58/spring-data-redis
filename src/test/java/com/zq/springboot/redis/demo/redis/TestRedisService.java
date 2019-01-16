package com.zq.springboot.redis.demo.redis;

import ch.qos.logback.core.encoder.EchoEncoder;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisService {
	@Autowired
	private RedisService redisService;

    @Test
    public void testString() throws Exception {
        redisService.set("neo", "ityouknow");
//        junit.framework包下的Assert提供了多个断言方法. 主用于比较测试传递进去的两个参数.
//        Assert.assertEquals();及其重载方法: 1. 如果两者一致, 程序继续往下运行. 2. 如果两者不一致, 中断测试方法, 抛出异常信息 AssertionFailedError .
        Assert.assertEquals("ityouknow", redisService.get("neo"));
    }
    
    @Test
    public void testObj() throws Exception {
        User user=new User("ityouknow@126.com", "smile", "youknow", "know","2020");
        redisService.set("user",user);
        User userR=(User) redisService.get("user");
        System.out.println("User from redis:------------>"+userR.toString());
    }

    @Test
    public void testExpire() throws Exception{
        User user = new User("expire","smild","password","username","2020");
        redisService.set("user",user,1L);
        System.out.println("User from redis:------------>"+redisService.get("user"));
        Thread.sleep(1000);
        boolean result = redisService.exists("user");
        if(result){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }

    @Test
    public void testHash() throws Exception{
        redisService.hashSet("hash","you","you");
        String value=(String) redisService.hashGet("hash","you");
        System.out.println("hash value :"+value);
    }

    public void testList() throws Exception{
        String key="list";
        redisService.push(key,"it");
        redisService.push(key,"it");
        redisService.push(key,"you");
        redisService.push(key,"know");
        List<Object> value = redisService.range(key,0L,3L);
        System.out.println("list value :"+value.toString());
    }

}
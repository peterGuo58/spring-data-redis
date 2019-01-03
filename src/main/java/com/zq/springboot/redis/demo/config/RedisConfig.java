package com.zq.springboot.redis.demo.config;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

//缓存配置
//        在这里我们可以为 Redis 设置一些全局配置，比如配置主键的生产策略 KeyGenerator，如不配置会默认使用参数名作为主键。
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

	@Bean
	public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheManager rcm =  RedisCacheManager.create(redisConnectionFactory);
        //设置缓存过期时间
        //rcm.setDefaultExpiration(60);//秒
        return rcm;
    }

    public  static void main(String[] args){
	    String ss = "Hello";
	    String sss = ss.intern();
	    System.out.println( ss == sss );

    }
}
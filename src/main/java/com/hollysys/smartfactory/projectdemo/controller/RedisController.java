package com.hollysys.smartfactory.projectdemo.controller;

import com.hollysys.smartfactory.projectdemo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author sanelee
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    /**redis中存储的过期时间60s*/
    private static int ExpireTime = 60;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping(value = "set",method = RequestMethod.POST)
    public boolean redisset(String key, String value){
        log.info("传入的键值对"+key+":"+value);

        //return redisUtil.set(key,userEntity,ExpireTime);

        return redisUtil.set(key,value);
    }

    @GetMapping("get/{key}")
    public Object redisget(@PathVariable String key){
        return redisUtil.get(key);
    }

    @RequestMapping(value = "hset",method = RequestMethod.POST)
    public Object redisHset(String key,String item,String value){
        return redisUtil.hset(key,item,value);
    }

    @RequestMapping("hget")
    public Object redisHget(String item,String key){
        return redisUtil.hget(key,item);
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }
}

package com.hollysys.smartfactory.projectdemo.service.impl;


import com.hollysys.smartfactory.projectdemo.mapper.TargetItemDao;
import com.hollysys.smartfactory.projectdemo.model.entity.TargetModelItem;
import com.hollysys.smartfactory.projectdemo.service.TargetModelItemService;
import com.hollysys.smartfactory.projectdemo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizhi186545
 */
@Service("targetModelItemService")
//@CacheConfig(cacheNames = "item")
public class TargetModelItemServiceImpl implements TargetModelItemService {
    @Autowired
    private TargetItemDao targetItemDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;




    @Override
//    @Transactional
//    @CachePut(key = "#modelItem.id")
    public TargetModelItem insert(TargetModelItem modelItem) {
        ValueOperations<String,List<TargetModelItem>> operations = redisTemplate.opsForValue();
        String key = "items";
        List<TargetModelItem> targetModelItems = operations.get(key);
        boolean hasKey = targetModelItems.contains(modelItem);
        if (!hasKey){
            operatAdd(modelItem.getId(),modelItem,targetModelItems);
//            targetModelItems.add(modelItem.getId()-1,modelItem);
            operations.set(key,targetModelItems);
            redisUtil.expire(key,60);
        }
        this.targetItemDao.insert(modelItem);
        return modelItem;
    }

    @Override
    public boolean checkId(Integer id) {
        TargetModelItem targetModelItem = targetItemDao.selectById(id);
        if (targetModelItem != null) {
            return true;
        } else {
            return false;
        }
    }

//    @Cacheable(key = "")
    @Override
    public List<TargetModelItem> findAll() {
        String key = "items";
        ValueOperations<String,List<TargetModelItem>> operations = redisTemplate.opsForValue();
        //????????????
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey){
            List<TargetModelItem> targetModelItems = operations.get(key);
            redisUtil.expire(key,60);
            return targetModelItems;
        }else {
            //???mySQL???????????????
            List<TargetModelItem> targetModelItems = targetItemDao.findAll();
            //????????????
            operations.set(key,targetModelItems);
            redisUtil.expire(key,60);
//            System.out.println(operations.get(key));
            return targetModelItems;
        }
//        return this.targetItemDao.findAll();
    }

    @Override
//    @CacheEvict(key = "#id")
    public boolean deleteById(Integer id) {
        int ret = targetItemDao.deleteById(id);
        //???????????????????????????
        String key = "items";
        String idKey = "items_"+id;
        boolean hasKey = redisTemplate.hasKey(idKey);
        ValueOperations<String,List<TargetModelItem>> operations = redisTemplate.opsForValue();
        List<TargetModelItem> targetModelItems = operations.get(key);
        for(int i=0;i<targetModelItems.size();i++){
            if (targetModelItems.get(i).getId().equals(id)){
                targetModelItems.remove(i);
                operations.set(key,targetModelItems);
                redisUtil.expire(key,60);
            }
        }
        if (hasKey) {
            redisTemplate.delete(idKey);
        }
        return ret > 0;
    }

    @Override
//    @CachePut(key="#modelItem.id")
    public TargetModelItem update(TargetModelItem modelItem) {
        ValueOperations<String,List<TargetModelItem>> operations = redisTemplate.opsForValue();
        String key = "items";
        String idKey = "items_"+modelItem.getId();
        List<TargetModelItem> targetModelItems = operations.get(key);
        boolean hasKey = targetModelItems.contains(modelItem);
        boolean hasKeyId = redisTemplate.hasKey(idKey);
        //??????????????????????????????,?????????????????????????????????
        if (!hasKey){
            targetModelItems.remove(modelItem.getId()-1);
            operatAdd(modelItem.getId(),modelItem,targetModelItems);
            operations.set(key,targetModelItems);
            redisUtil.expire(key,60);
        }
        if (hasKeyId){
            redisTemplate.delete(idKey);
        }
        this.targetItemDao.update(modelItem);
        return modelItem;
    }

//    @Cacheable(key="#id",unless="#result == null")
    @Override
    public TargetModelItem selectById(Integer id) {
        String key = "items_"+id;
        ValueOperations<String,TargetModelItem> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            TargetModelItem targetModelItem= operations.get(key);
            redisUtil.expire(key,60);
            return targetModelItem;
        }else {
            // ??? DB ?????????????????????
            TargetModelItem targetModelItem = targetItemDao.selectById(id);

            // ????????????
            operations.set(key, targetModelItem);
            redisUtil.expire(key,60);
            return targetModelItem;
        }
    }

    public void operatAdd(int id,TargetModelItem modelItem,List<TargetModelItem> targetModelItems){
        if (id<=targetModelItems.size()){

            targetModelItems.add(id-1,modelItem);
        }else {
            modelItem.setId(targetModelItems.size()+1);
            targetModelItems.add(modelItem);
        }

    }
}

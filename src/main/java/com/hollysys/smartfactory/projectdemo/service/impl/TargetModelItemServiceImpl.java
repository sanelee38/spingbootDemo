package com.hollysys.smartfactory.projectdemo.service.impl;


import com.hollysys.smartfactory.projectdemo.mapper.TargetItemDao;
import com.hollysys.smartfactory.projectdemo.model.entity.TargetModelItem;
import com.hollysys.smartfactory.projectdemo.service.TargetModelItemService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lizhi186545
 */
@Service("targetModelItemService")
@CacheConfig(cacheNames = "targetItem")
public class TargetModelItemServiceImpl implements TargetModelItemService {
    @Resource
    private TargetItemDao targetItemDao;


    @Override
    public TargetModelItem insert(TargetModelItem modelItem) {
        this.targetItemDao.insert(modelItem);
        return modelItem;
    }

    @Override
    public boolean checkId(String id) {
        TargetModelItem targetModelItem = targetItemDao.selectById(id);
        if (targetModelItem != null) {
            return true;
        } else {
            return false;
        }
    }

    @Cacheable(cacheNames = "TargetModelItem",key = "methodName +#id")
    @Override
    public List<TargetModelItem> findAll() {
        return this.targetItemDao.findAll();
    }

    @Override
    public boolean deleteById(String id) {
        return targetItemDao.deleteById(id) > 0;
    }

    @Override
    public TargetModelItem update(TargetModelItem modelItem) {
        this.targetItemDao.update(modelItem);
        return modelItem;
    }

    @Cacheable(cacheNames = "item",key = "#id")
    @Override
    public TargetModelItem selectById(String id) {
        return this.targetItemDao.selectById(id);
    }
}

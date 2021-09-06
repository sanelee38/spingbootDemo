package com.hollysys.smartfactory.projectdemo.mapper;


import com.hollysys.smartfactory.projectdemo.model.entity.TargetModelItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author lizhi186545
 */
@Mapper
public interface TargetItemDao {

    /**
     * 新增
     * @param modelItem modelItem
     * @return 行数
     */
    int insert(TargetModelItem modelItem);

    /**
     * 查询所有
     * @return TargetModelItem数组
     */

    List<TargetModelItem> findAll();

    /**
     * 根据id删除
     * @param id id
     * @return 行数
     */
    int deleteById(String id);

    /**
     * 更新
     * @param modelItem modelItem对象
     * @return 行数
     */
    int update(TargetModelItem modelItem);

    /**
     * 根据id查找
     * @param id id
     * @return TargetModelItem对象
     */

    TargetModelItem selectById(String id);
}

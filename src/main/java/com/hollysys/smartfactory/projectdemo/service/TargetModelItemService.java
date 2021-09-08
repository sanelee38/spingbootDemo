package com.hollysys.smartfactory.projectdemo.service;



import com.hollysys.smartfactory.projectdemo.model.entity.TargetModelItem;

import java.util.List;


/**
 * @author lizhi186545
 */
public interface TargetModelItemService {

    TargetModelItem insert(TargetModelItem modelItem);

    boolean checkId(Integer id);

    List<TargetModelItem> findAll();

    boolean deleteById(Integer id);

    TargetModelItem update(TargetModelItem modelItem);

    TargetModelItem selectById(Integer id);
}

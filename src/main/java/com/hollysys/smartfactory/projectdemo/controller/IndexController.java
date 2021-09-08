package com.hollysys.smartfactory.projectdemo.controller;


import com.hollysys.smartfactory.projectdemo.model.TestEvent;
import com.hollysys.smartfactory.projectdemo.model.entity.TargetModelItem;
import com.hollysys.smartfactory.projectdemo.model.resp.ResultStatus;
import com.hollysys.smartfactory.projectdemo.model.resp.ReturnInfo;
import com.hollysys.smartfactory.projectdemo.service.TargetModelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author lizhi186545
 */
@RestController
@RequestMapping("/target")
public class IndexController extends BaseController {
    @Autowired
    private TargetModelItemService targetModelItemService;

    @Autowired
    private ApplicationContext publisher;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultStatus add(@RequestBody TargetModelItem modelItem) {
        boolean result = targetModelItemService.checkId(modelItem.getId());
        if (result) {
            return error(ReturnInfo.SAVE_FAIL_MSG);
        } else {
            return success(ReturnInfo.SAVE_SUCCESS_MSG, targetModelItemService.insert(modelItem));
        }
    }


    @GetMapping("/findAll")
    public ResultStatus findAll() {
        List<TargetModelItem> all = targetModelItemService.findAll();
        if (all.size() == 0) {
            return error(ReturnInfo.QUERY_FAIL_MSG);
        } else {
            return success(ReturnInfo.QUERY_SUCCESS_MSG, all);
        }
    }

    @GetMapping("/query/{id}")
    public ResultStatus selectById(@PathVariable Integer id) {
        TargetModelItem targetModelItem = targetModelItemService.selectById(id);
        if (targetModelItem != null) {
            return success(ReturnInfo.QUERY_SUCCESS_MSG, targetModelItem);
        } else {
            return error(ReturnInfo.QUERY_FAIL_MSG);
        }
    }

    @GetMapping("/delete/{id}")
    public ResultStatus delete(@PathVariable Integer id) {
        boolean b = targetModelItemService.deleteById(id);
        if (!b) {
            return error(ReturnInfo.DEL_FAIL_MSG);
        } else {
            return success(ReturnInfo.DEL_SUCCESS_MSG);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultStatus update(@RequestBody TargetModelItem modelItem) {
        boolean result = targetModelItemService.checkId(modelItem.getId());
        if (result) {
            return success(ReturnInfo.UPDATE_SUCCESS_MSG, targetModelItemService.update(modelItem));
        } else {
            return error(ReturnInfo.UPDATE_FAIL_MSG);
        }
    }

    @GetMapping("/testListen")
    public void testListen(){
        for (int i = 0;i<10;i++){
            System.out.println("i="+i);
        }
        publisher.publishEvent(new TestEvent(this,"发布了测试事件"));

        for (int j =0;j<10;j++){
            System.out.println("j = "+j);
        }
    }
}

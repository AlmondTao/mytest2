package com.taoqy.controller;

import com.taoqy.entity.Child;
import com.taoqy.entity.Father;
import com.taoqy.entity.Mother;
import com.taoqy.service.TBFZServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/5/28
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@RestController
public class TBFZController {

    @Autowired
    private TBFZServiceImpl tbfzService;

    public String createFamily(Father fatherMessage, Mother motherMessage, List<Child> childList){
        tbfzService.createFamily(fatherMessage, motherMessage, childList);
        return "";
    }

    public String updateFamily(Father fatherMessage, Mother motherMessage, List<Child> childList){
        tbfzService.updateFamily(fatherMessage, motherMessage, childList);
        return "";
    }

    public String sendPerson(int personId){
        tbfzService.sendPerson(personId);
        return "";
    }
}

package com.taoqy.controller;

import com.taoqy.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/4/10
 * @see [相关类/方法]
 */
@RestController
@RequestMapping("ribbon")
public class RibbonController {
    @Autowired
    private RibbonService ribbonService;

    @RequestMapping("sayHello")
    public String sayHello(){
        return ribbonService.swaggerSayHello();
    }
}

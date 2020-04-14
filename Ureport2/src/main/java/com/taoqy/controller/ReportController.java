package com.taoqy.controller;

import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
public class ReportController {

    public void testController(){
        ArrayList<String> objects = new ArrayList<>();
        objects.stream().filter(t->{
            return true;
        });
    }

}

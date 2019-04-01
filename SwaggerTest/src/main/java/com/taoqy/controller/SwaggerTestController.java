package com.taoqy.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/4/1
 * @see [相关类/方法]
 */
@RestController
@RequestMapping("swaggerTest")
public class SwaggerTestController {

    @RequestMapping(value = "sayHello",method = RequestMethod.GET)
    @ApiOperation(value = "跟系统打声招呼",notes = "")
    public String sayHello(){
        return "hello";
    }
    //swagger可以识别RequestParam注解里的require的值
    @ApiOperation(value = "说点什么",notes = "说的内容")
    @RequestMapping(value = "saySomeThing",method = RequestMethod.POST)
    public String saySomeThing(@RequestParam(value = "str",required = true) String str){
        return str;
    }
    //swagger可以识别RequestParam中的value name作为Parameter,word作为description
    @ApiOperation(value = "和我聊天",notes = "可传可不传")
    @RequestMapping(value = "talkToMe",method = RequestMethod.PUT)
    public String talkToMe(@RequestParam(value = "name",required = false)String word){
        return StringUtils.isEmpty(word)? "hello,my name is Almond":"hello,my name is "+word;
    }
}

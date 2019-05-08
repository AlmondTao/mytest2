package com.taoqy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/4/10
 * @see [相关类/方法]
 */
@Service
public class RibbonService {
    @Autowired
    private RestTemplate restTemplate;

    public String swaggerSayHello(){
        return restTemplate.getForObject("http://SWAGGER-TEST/swaggerTest/sayHello", String.class);
    }
}

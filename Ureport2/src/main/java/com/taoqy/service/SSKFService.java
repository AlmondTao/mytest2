package com.taoqy.service;

import com.taoqy.entity.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/7/5
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@Service("SSKFService")
public class SSKFService {

    public List<Person> getPerson(String dsName, String datasetName, Map<String,Object> parameters){
        String personName = (String) parameters.get("personName");
        String personAge = (String) parameters.get("personAge");
        //dao.select
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(personName, personAge));
        personList.add(new Person("1", "1"));
        personList.add(new Person("2", "2"));
        personList.add(new Person("3", "3"));
        personList.add(new Person("4", "4"));
        return personList;

    }
}

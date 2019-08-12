package com.taoqy.entity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/5/28
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
public class Person {
    private String personName;
            private String personAge;

    public String getPersonName() {
        return personName;
    }

    public Person(String personName, String personAge) {
        this.personName = personName;
        this.personAge = personAge;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonAge() {
        return personAge;
    }

    public void setPersonAge(String personAge) {
        this.personAge = personAge;
    }
}

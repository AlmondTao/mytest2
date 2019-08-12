package com.taoqy.service;

import com.taoqy.dao.TBFZDao;
import com.taoqy.entity.Child;
import com.taoqy.entity.Father;
import com.taoqy.entity.Mother;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class TBFZServiceImpl {

    @Autowired
    private TBFZDao tbfzfDao;

    public void createFamily(Father fatherMessage, Mother motherMessage, List<Child> childList) {
        tbfzfDao.insertPerson(fatherMessage);
        tbfzfDao.insertChild(childList.get(0));
    }

    public void updateFamily(Father fatherMessage, Mother motherMessage, List<Child> childList) {
        tbfzfDao.updatePerson(fatherMessage);
        tbfzfDao.updateChild(childList.get(0));
    }

    public void sendPerson(int personId) {
        tbfzfDao.updatePersonStatusByPersonId(personId, "2");
    }
}

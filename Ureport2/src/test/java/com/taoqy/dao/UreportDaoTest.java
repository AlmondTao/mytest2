package com.taoqy.dao;

import com.taoqy.util.MybatisUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class UreportDaoTest {

    UreportDao ureportDao = MybatisUtils.getMapper(UreportDao.class);

    @Test
    public void insertTemplate() {
    }

    @Test
    public void selectAllTemplate() {
        Object o = ureportDao.selectAllTemplate();
    }
}
package com.taoqy.dao;

import com.taoqy.entity.Template;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface UreportDao {



    @Insert("INSERT " +
            "INTO " +
            "TEMPLATE " +
            "(TEMPLATE_NAME,TEMPLATE_CONTENT,CHANGE_DATE) " +
            "VALUES " +
            "(#{templateName},#{templateContent},#{changeDate})")
    void insertTemplate(Template template);


    @Results({
            @Result(property = "templateId",column = "TEMPLATE_ID",javaType = Integer.class),
            @Result(property = "templateName",column = "TEMPLATE_NAME",javaType = String.class),
            @Result(property = "templateContent",column = "TEMPLATE_CONTENT",javaType = String.class),
            @Result(property = "changeDate",column = "CHANGE_DATE",javaType = Date.class),
    })
    @Select("SELECT " +
            "* " +
            "FROM " +
            "TEMPLATE  ")
    List<Template> selectAllTemplate();


    @Select("SELECT " +
            "* " +
            "FROM " +
            "TEMPLATE " +
            "WHERE " +
            "TEMPLATE_NAME = #{file}")
    Template selectTemplateByName(String file);
}

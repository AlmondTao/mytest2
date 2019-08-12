package com.taoqy.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionTemplate;

public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory;


    static {

        //Mybatis配置类
        Configuration configuration = new Configuration();

        //配置数据源
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/ureport?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8");
        dataSource.setUser("taoqy");
        dataSource.setPassword("password123");

        //构建Mybatis环境
        Environment et = new Environment("100", new JdbcTransactionFactory(), dataSource);
        configuration.setEnvironment(et);

        //注册Mapper
        long start = System.currentTimeMillis();
        for (Class<?> e : EntityHelper.getPackageClassByAnnotation("com.taoqy", Mapper.class)) {
            configuration.addMapper(e);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    public static void pr(Object o){
        System.out.println(o);
    }

    private static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() {
        return getSqlSessionFactory().openSession();
    }

    public static SqlSessionTemplate getSqlSessionTpl(){
        return new SqlSessionTemplate(getSqlSessionFactory());
    }

    public static <T> T getMapper(Class<T> tClass) {
        return getSqlSessionFactory().openSession().getMapper(tClass);
    }

    public static void main(String[] args) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
       /* RecordManageMapper recordManageMapper = sqlSession.getMapper(RecordManageMapper.class);
        ArchitecturalDesign design = new ArchitecturalDesign();
        design.setBuilding_type(Common.BUILDING_TYPE_PRIVATE);
        design.setBasic_information_id(1);
        recordManageMapper.test(design);
        ArchitecturalDesign obj = recordManageMapper.findOne(4);
        System.out.println(obj.getBuilding_type());
        System.out.println(obj.getBuilding_type().toString());*/
        sqlSession.commit();
        sqlSession.close();
    }
}

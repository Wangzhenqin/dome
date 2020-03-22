package com.imooc.config.dao;


import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SessionFactoryConfiguration {
    //实体类所在包
    @Value("${entity_package}")
    private String entityPackage;
    //mybatic_config.xml配置文件的路径
    @Value("${mybatis_config_file}")
    private String mybatisConfigFilePath;
    //mybatis mapper文件路径
    @Value("${mapper_path}")
    private String mapperPath;
    @Autowired
    private DataSource dataSource;

    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));
        PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        String packageSearchPath=PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+mapperPath;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
        return sqlSessionFactoryBean;
    }


}

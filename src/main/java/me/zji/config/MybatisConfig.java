package me.zji.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.PreDestroy;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Spring-Mybatis 配置
 * Created by imyu on 2017/2/11.
 */
@Configuration
@ComponentScan("me.zji.service.**")
@MapperScan("me.zji.dao")
public class MybatisConfig {

    // 配置文件，提供jdbc连接信息
    @Bean
    public Properties applicationProperties() throws IOException{
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resources = resolver.getResource("classpath:me/zji/config/application.properties");
        Properties properties = new Properties();
        InputStream inputStream = new BufferedInputStream(resources.getInputStream());
        properties.load(inputStream);
        return properties;
    }
    // 配置数据源
    @Bean
    @PreDestroy
    public BasicDataSource dataSource(@Qualifier("applicationProperties") Properties properties) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
        basicDataSource.setUrl(properties.getProperty("jdbc.url"));
        basicDataSource.setUsername(properties.getProperty("jdbc.username"));
        basicDataSource.setPassword(properties.getProperty("jdbc.password"));
        return basicDataSource;
    }
    // Spring与Mybatis整合，不需要mybatis的配置映射文件
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(BasicDataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setTypeAliasesPackage("me.zji.entity");
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:me/zji/dao/impl/*.xml"));
        return sqlSessionFactoryBean;
    }
    // DAO接口所在包名，Spring会自动查找其下的类
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("me.zji.dao");
        return mapperScannerConfigurer;
    }
    // (事务管理)transaction manager, use JtaTransactionManager for global tx
    @Bean
    public DataSourceTransactionManager transactionManager(BasicDataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}

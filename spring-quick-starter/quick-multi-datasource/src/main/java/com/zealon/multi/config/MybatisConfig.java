package com.zealon.multi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zealon.multi.datasource.DataSourceEnum;
import com.zealon.multi.datasource.RoutingDataSource;
import com.zealon.multi.utils.BDException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Mybatis & DataSource & Mapper & PageHelper 配置
 */
@Configuration
public class MybatisConfig {
    
	/**
     * 配置默认数据源
     */
    @Primary
    @Bean
    public DataSource dataSourceMaster(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(getConfig().getString("master.driverClassName"));
        ds.setUrl(getConfig().getString("master.url"));
        ds.setUsername(getConfig().getString("master.username"));
        ds.setPassword(getConfig().getString("master.password"));
        ds.setInitialSize(Integer.parseInt(getConfig().getString("master.initialSize")));
        ds.setMinIdle(Integer.parseInt(getConfig().getString("master.minIdle")));
        ds.setMaxActive(Integer.parseInt(getConfig().getString("master.maxActive")));
        ds.setMaxWait(Long.parseLong(getConfig().getString("master.maxActive")));
        return ds;
    }

    /**
     * quartz数据源
     */
    @Bean
    public DataSource dataSourceOther(){            
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(getConfig().getString("other.driverClassName"));
        ds.setUrl(getConfig().getString("other.url"));
        ds.setUsername(getConfig().getString("other.username"));
        ds.setPassword(getConfig().getString("other.password"));
        ds.setInitialSize(Integer.parseInt(getConfig().getString("other.initialSize")));
        ds.setMinIdle(Integer.parseInt(getConfig().getString("other.minIdle")));
        ds.setMaxActive(Integer.parseInt(getConfig().getString("other.maxActive")));
        ds.setMaxWait(Long.parseLong(getConfig().getString("other.maxActive")));
        return ds;
    }
    
    /**
     * sqlserver数据源
     */
    @Bean
    public DataSource dataSourceSqlServer(){            
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(getConfig().getString("sqlserver.driverClassName"));
        ds.setUrl(getConfig().getString("sqlserver.url"));
        ds.setUsername(getConfig().getString("sqlserver.username"));
        ds.setPassword(getConfig().getString("sqlserver.password"));
        ds.setInitialSize(Integer.parseInt(getConfig().getString("sqlserver.initialSize")));
        ds.setMinIdle(Integer.parseInt(getConfig().getString("sqlserver.minIdle")));
        ds.setMaxActive(Integer.parseInt(getConfig().getString("sqlserver.maxActive")));
        ds.setMaxWait(Long.parseLong(getConfig().getString("sqlserver.maxActive")));
        return ds;
    }
    
    /**
     * 装配所有数据源
     */
    public RoutingDataSource dataSourceConfig(){
    	//动态数据源
    	RoutingDataSource myRoutingDataSource = new RoutingDataSource();
    	//放入数据源
    	Map<Object,Object> targetDataSources = new HashMap<Object,Object>();
    	targetDataSources.put(DataSourceEnum.MASTER, dataSourceMaster());
    	targetDataSources.put(DataSourceEnum.OTHER, dataSourceOther());
    	targetDataSources.put(DataSourceEnum.SQLSERVER, dataSourceSqlServer());
    	myRoutingDataSource.setTargetDataSources(targetDataSources);
    	//设置默认数据源
    	myRoutingDataSource.setDefaultTargetDataSource(dataSourceMaster());
    	myRoutingDataSource.afterPropertiesSet();
    	return myRoutingDataSource;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        //factory.setDataSource(dataSource);  //单数据源方式
        factory.setDataSource(dataSourceConfig());

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath:com/zealon/**/mapper/*.xml"));
        return factory.getObject();
    }
 
    /**
     * 获取数据库链接配置信息
     */
    public static org.apache.commons.configuration.Configuration getConfig() {
        try {
            return new PropertiesConfiguration("datasources.properties");
        } catch (ConfigurationException e) {
            throw new BDException("获取配置文件失败，", e);
        }
    }
}


package cn.zealon.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingjdbc.core.api.algorithm.masterslave.MasterSlaveLoadBalanceAlgorithmType;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.jdbc.core.datasource.MasterSlaveDataSource;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @auther: Zealon
 * @Date: 2018-10-10 13:51
 */
@Configuration
@MapperScan(basePackages = "cn.zealon.**.dao")
public class DataSourceConfig {

    /**
     * 配置分库分表策略
     *
     * @return
     * @throws SQLException
     */
    @Bean(name = "shardingDataSource")
    DataSource getShardingDataSource() throws SQLException {
        //MasterSlaveDataSource
        //https://segmentfault.com/a/1190000015563280
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.setDefaultDataSourceName("master");
        shardingRuleConfig.setMasterSlaveRuleConfigs(masterSlaveRuleConfigs());
        return new ShardingDataSource(shardingRuleConfig.build(createDataSourceMap()));

    }

    //主从规则配置
    @Bean
    public List<MasterSlaveRuleConfiguration> masterSlaveRuleConfigs(){

        List<MasterSlaveRuleConfiguration> configurations = new ArrayList<MasterSlaveRuleConfiguration>();

        // 从库集合
        List<String> slaveDataSourceNames = new ArrayList<String>();
        slaveDataSourceNames.add("slave_0");
        slaveDataSourceNames.add("slave_1");

        // 主从规则配置
        MasterSlaveRuleConfiguration ruleConfiguration = new MasterSlaveRuleConfiguration();
        ruleConfiguration.setName("dataSource");
        ruleConfiguration.setMasterDataSourceName("master");
        ruleConfiguration.setSlaveDataSourceNames(slaveDataSourceNames);
        ruleConfiguration.setLoadBalanceAlgorithmType(MasterSlaveLoadBalanceAlgorithmType.ROUND_ROBIN);

        // 添加主从规则
        configurations.add(ruleConfiguration);
        return configurations;
    }

    /**
     * 需要手动配置事务管理器
     *
     * @param shardingDataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactitonManager(DataSource shardingDataSource) {
        return new DataSourceTransactionManager(shardingDataSource);
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(shardingDataSource);

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver.getResources("classpath:cn/zealon/**/mapper/*.xml"));
        return factory.getObject();
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("master", createDataSource("ds_master"));
        result.put("slave_0", createDataSource("ds_slave_0"));
        result.put("slave_1", createDataSource("ds_slave_1"));
        return result;
    }

    private DataSource createDataSource(final String dataSourceName) {
        DruidDataSource result = new DruidDataSource();
        result.setDriverClassName("com.mysql.jdbc.Driver");
        result.setUrl(String.format("jdbc:mysql://localhost:3306/%s", dataSourceName));
        result.setUsername("root");
        result.setPassword("pass123");
        return result;
    }

}

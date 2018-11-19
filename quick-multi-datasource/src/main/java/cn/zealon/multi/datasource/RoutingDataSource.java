package cn.zealon.multi.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源切换
 * 
 */
public class RoutingDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		DataSourceEnum dataSource = DataSourceContextHolder.getTargetDataSource();
		return dataSource;
	}

}

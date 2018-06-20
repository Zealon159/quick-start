package cn.zealon.multi.datasource;

public enum DataSourceEnum {
	/**
	 * 默认数据源
	 */
	MASTER("db_master"),
	/**
	 * Other数据源
	 */
	OTHER("Other"),
	/**
	 * sqlserver数据源
	 */
	SQLSERVER("db_sqlserver");
	
    private String datasource;

    DataSourceEnum( final String datasource){
        this.datasource=datasource;
    }

    public String getDataSource() {
        return datasource;
    }
}

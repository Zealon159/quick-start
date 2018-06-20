package cn.zealon.multi.datasource.aop;

import java.lang.annotation.*;

import cn.zealon.multi.datasource.DataSourceEnum;


/**
 * 创建拦截设置数据源的注解
 * 
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
	DataSourceEnum dataSource() default DataSourceEnum.MASTER;
}
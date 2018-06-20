package cn.zealon.multi.datasource.aop;

import cn.zealon.multi.datasource.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Aspect
@Component
@Order(-1)
public class HandlerDataSourceAop {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @within在类上设置
	 * @annotation在方法上进行设置
	 */
    @Pointcut("@within(cn.zealon.multi.datasource.aop.TargetDataSource)||@annotation(cn.zealon.multi.datasource.aop.TargetDataSource)")
    public void pointcut() {}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint)
    {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        TargetDataSource annotationClass = method.getAnnotation(TargetDataSource.class);//获取方法上的注解
        if(annotationClass == null){
            annotationClass = joinPoint.getTarget().getClass().getAnnotation(TargetDataSource.class);//获取类上面的注解
            if(annotationClass == null) return;
        }
        //获取注解上的数据源的值的信息
        if(annotationClass.dataSource() !=null){
            //给当前的执行SQL的操作设置特殊的数据源的信息
            DataSourceContextHolder.setTargetDataSource(annotationClass.dataSource());
        }
        logger.info("切换数据源,className:"+joinPoint.getTarget().getClass().getName()+"  "+annotationClass.dataSource());
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        DataSourceContextHolder.resetDefaultDataSource();
    }
}

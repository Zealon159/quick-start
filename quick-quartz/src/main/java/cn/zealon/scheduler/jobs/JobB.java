package cn.zealon.scheduler.jobs;

import cn.zealon.scheduler.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

public class JobB implements BaseJob {

    private static Logger log = LoggerFactory.getLogger(JobB.class);  

    public void execute(JobExecutionContext context)  
        throws JobExecutionException {  
    	System.out.println("JobB 执行时间: " + new Date());
    	log.info("JobB 执行时间: " + new Date());  

    }  
}

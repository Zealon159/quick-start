package cn.zealon.scheduler.jobs;

import cn.zealon.scheduler.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JobA implements BaseJob {

    private static Logger log = LoggerFactory.getLogger(JobA.class);  

    public void execute(JobExecutionContext context) throws JobExecutionException {  

    	log.info("JobAAAAA 执行时间: " + new Date());  

    }  
}

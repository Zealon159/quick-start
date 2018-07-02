package cn.zealon.scheduler.service.impl;

import cn.zealon.common.Result;
import cn.zealon.common.ResultUtil;
import cn.zealon.common.util.PackageUtil;
import cn.zealon.common.util.PageConvertUtil;
import cn.zealon.scheduler.BaseJob;
import cn.zealon.scheduler.dao.JobAndTriggerMapper;
import cn.zealon.scheduler.entity.JobAndTrigger;
import cn.zealon.scheduler.service.JobAndTriggerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAndTriggerServiceImpl implements JobAndTriggerService {
	
	private static Logger log = LoggerFactory.getLogger(JobAndTriggerServiceImpl.class); 
	
	@Autowired
	private JobAndTriggerMapper jobAndTriggerMapper;
	
	@Autowired 
	@Qualifier("Scheduler")  //加入Qulifier注解，通过名称注入bean
	private Scheduler scheduler;

	public String getJobAndTriggerDetails(int page, int limit) {
		PageHelper.startPage(page, limit);
		Page<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
		return PageConvertUtil.getGridJson(list);
	}
	
	public Result addJob(String jobName, String jobClassName, String jobGroupName, String cronExpression
			, String jobDescription) throws Exception{
		
		Result result = ResultUtil.success();
		// 启动调度器  
		scheduler.start(); 
		
		//构建job信息
		JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
				.withDescription(jobDescription)
				.withIdentity(jobName, jobGroupName).build();
		
		//表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
        		.withDescription(jobDescription)
        		.withSchedule(scheduleBuilder).build();
        
        try {
        	scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
        	result = ResultUtil.fail();
        	log.error("创建定时任务失败:"+e);
            throw new Exception("创建定时任务失败!");
        }
        return result;
	}
	
	public Result pause(String jobName, String jobGroupName) throws Exception{	
		scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
		return ResultUtil.success();
	}
	
	public Result resume(String jobName, String jobGroupName) throws Exception {
		scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
		return ResultUtil.success();
	}
	
	public Result reschedule(String jobName, String jobGroupName, String cronExpression) throws Exception {				
		Result result = ResultUtil.success();
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			result = ResultUtil.fail();
			log.error("更新定时任务失败:"+e);
			throw new Exception("更新定时任务失败!");
		}
		return result;
	}
	
	/**
	 * 删除任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @throws Exception
	 */
	public Result delete(String jobName, String jobGroupName) throws Exception{
		Result result = ResultUtil.success();
		try{
			scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
			scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
			scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));			
		}catch(Exception e){
			result = ResultUtil.fail();
			System.out.println("删除定时任务失败"+e);
			throw new Exception("更新定时任务失败");
		}
		return result;
	}
	
	public static BaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (BaseJob)class1.newInstance();
	}
	
	public String getJobsClassList(String packagePath){
		StringBuffer json = new StringBuffer();
		List<String> classNames = PackageUtil.getClassName(packagePath, true);
		json.append("[");
		for(int i=0;i<classNames.size();i++){
			if(i>0){json.append(",");}
			String className = classNames.get(i);
			json.append("{\"id\":\""+className+"\",\"text\":\""+className+"\"}");
		}
		json.append("]");
		return json.toString();
	}
}

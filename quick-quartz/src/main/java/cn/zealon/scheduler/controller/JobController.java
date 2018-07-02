package cn.zealon.scheduler.controller;

import cn.zealon.common.Result;
import cn.zealon.scheduler.service.JobAndTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 任务调度控制器
 * 
 */
@Controller
@RequestMapping(value="scheduler")
public class JobController { 
	
	@Autowired
	private JobAndTriggerService jobAndTriggerService;
	
	@GetMapping(value="/add")
	public String add() {			
		return "scheduler/job_add";
	}
	
	//新增任务
	@ResponseBody
	@PostMapping(value="/save")
	public Result save(@RequestParam(value="jobName")String jobName, 
			@RequestParam(value="jobClassName")String jobClassName, 
			@RequestParam(value="jobGroupName")String jobGroupName, 
			@RequestParam(value="cronExpression")String cronExpression,
			@RequestParam(value="jobDescription")String jobDescription) throws Exception {
		
		return jobAndTriggerService.addJob(jobName,jobClassName, jobGroupName, cronExpression,jobDescription);
	}
	
	//暂停
	@ResponseBody
	@PostMapping(value="/pause")
	public Result pause(@RequestParam(value="jobName")String jobName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception{			
		return jobAndTriggerService.pause(jobName, jobGroupName);
	}

	//继续任务
	@ResponseBody
	@PostMapping(value="/resume")
	public Result resume(@RequestParam(value="jobName")String jobName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception
	{			
		return jobAndTriggerService.resume(jobName, jobGroupName);
	}
	
	//重新部署任务
	@ResponseBody
	@PostMapping(value="/reschedule")
	public Result reschedule(@RequestParam(value="jobName")String jobName, 
			@RequestParam(value="jobGroupName")String jobGroupName,
			@RequestParam(value="cronExpression")String cronExpression) throws Exception{			
		return jobAndTriggerService.reschedule(jobName, jobGroupName, cronExpression);
	}
	
	//删除任务
	@ResponseBody
	@PostMapping(value="/delete")
	public Result delete(@RequestParam(value="jobName")String jobName, @RequestParam(value="jobGroupName")String jobGroupName) throws Exception {
		return jobAndTriggerService.delete(jobName, jobGroupName);
	}
	
	//查询任务
	@GetMapping(value="/list")
	public String list() {			
		return "scheduler/job_list";
	}
	
	//查询任务
	@ResponseBody
	@PostMapping(value="/listdata")
	public String queryjob(@RequestParam(value="page")Integer page, @RequestParam(value="limit")Integer limit) {			
		return jobAndTriggerService.getJobAndTriggerDetails(page, limit);
	}
	
	@ResponseBody
	@PostMapping(value="/classjson")
	public String getJobsClassList(){
		String packagePath = "cn.zealon.scheduler.jobs";
		return jobAndTriggerService.getJobsClassList(packagePath);
	}
	 
}

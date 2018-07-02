package cn.zealon.scheduler.service;


import cn.zealon.common.Result;

public interface JobAndTriggerService {
	/**
	 * 查询任务
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public String getJobAndTriggerDetails(int page, int limit);
	
	/**
	 * 创建调度任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @param cronExpression
	 */
	Result addJob(String jobName, String jobClassName, String jobGroupName, String cronExpression, String jobDescription) throws Exception;
	
	/**
	 * 暂停任务
	 * @param jobClassName
	 * @param jobGroupName
	 */
	Result pause(String jobClassName, String jobGroupName) throws Exception;
	
	/**
	 * 继续执行任务
	 * @param jobClassName
	 * @param jobGroupName
	 */
	Result resume(String jobClassName, String jobGroupName) throws Exception;
	
	/**
	 * 重新部署任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @param cronExpression
	 */
	Result reschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception;
	
	/**
	 * 删除任务
	 * @param jobClassName
	 * @param jobGroupName
	 * @throws Exception
	 */
	Result delete(String jobClassName, String jobGroupName) throws Exception;
	
	/**
	 * 获取包下所有类
	 * @param packagePath
	 * @return
	 */
	String getJobsClassList(String packagePath);
}

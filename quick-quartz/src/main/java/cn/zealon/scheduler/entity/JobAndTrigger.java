package cn.zealon.scheduler.entity;

import java.math.BigInteger;

public class JobAndTrigger {
	private String jobName;
	private String jobGroup;
	private String jobClassName;
	private String triggerName;
	private String triggerGroup;
	private String triggerState;
	private BigInteger startTime;
	private BigInteger endTime;
	private BigInteger repeatInterval;
	private BigInteger timesTriggerd;
	private String cronExpression;
	private String timeZoneId;
	private String description;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobClassName() {
		return jobClassName;
	}
	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}
	public String getTriggerName() {
		return triggerName;
	}
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	public String getTriggerGroup() {
		return triggerGroup;
	}
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	public String getTriggerState() {
		String stateName = "执行中";
		if(triggerState.equals("PAUSED")){
			stateName = "已暂停";
		}
		return stateName;
	}
	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}
	public BigInteger getStartTime() {
		return startTime;
	}
	public void setStartTime(BigInteger startTime) {
		this.startTime = startTime;
	}
	public BigInteger getEndTime() {
		return endTime;
	}
	public void setEndTime(BigInteger endTime) {
		this.endTime = endTime;
	}
	public BigInteger getRepeatInterval() {
		return repeatInterval;
	}
	public void setRepeatInterval(BigInteger repeatInterval) {
		this.repeatInterval = repeatInterval;
	}
	public BigInteger getTimesTriggerd() {
		return timesTriggerd;
	}
	public void setTimesTriggerd(BigInteger timesTriggerd) {
		this.timesTriggerd = timesTriggerd;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getTimeZoneId() {
		return timeZoneId;
	}
	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

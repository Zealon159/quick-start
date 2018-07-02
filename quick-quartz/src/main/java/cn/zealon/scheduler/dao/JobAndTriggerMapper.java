package cn.zealon.scheduler.dao;

import cn.zealon.scheduler.entity.JobAndTrigger;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobAndTriggerMapper {
	
	public Page<JobAndTrigger> getJobAndTriggerDetails();
	
}

package cn.zealon.multi.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

	@Select("select id,User_Name,Userid from org_user")
	public List<Map<String,Object>> selectAllByMasterDB();
	
	@Select("select id,User_Name,Userid from org_user")
	public List<Map<String,Object>> selectAllByOtherDB();
}

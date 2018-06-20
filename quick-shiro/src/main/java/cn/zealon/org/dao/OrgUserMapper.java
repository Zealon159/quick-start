package cn.zealon.org.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.zealon.org.entity.OrgUser;

@Mapper
public interface OrgUserMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(OrgUser record);

    OrgUser selectByPrimaryKey(String id);

    OrgUser getUserInfoByUserid(@Param("userId") String userId);

    int updateByPrimaryKey(OrgUser record);
    
    OrgUser selectByUserId(String userId);
    
}
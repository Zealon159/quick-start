package cn.zealon.org.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.zealon.org.entity.OrgUser;

@Mapper
public interface OrgUserMapper {

    int insert(OrgUser record);
    
    OrgUser selectByUserId(@Param("userId") String userId);
    
}
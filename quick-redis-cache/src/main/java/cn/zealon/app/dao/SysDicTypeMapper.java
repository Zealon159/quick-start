package cn.zealon.app.dao;

import cn.zealon.app.entity.SysDicType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDicTypeMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysDicType record);

    SysDicType selectByPrimaryKey(String id);

    int updateByPrimaryKey(SysDicType record);
    
    Page<SysDicType> selectAll();
    
    List<Map<String,Object>> selectAllByCombox();
    
    List<SysDicType> selectAllByCondition(Map<String, Object> params);
    
}
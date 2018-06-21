package cn.zealon.app.dao;

import cn.zealon.app.entity.SysDataDic;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface SysDataDicMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysDataDic record);

    SysDataDic selectByPrimaryKey(String id);

    Page<SysDataDic> selectAllByCondition(@Param("dictype") String dictype, @Param("searchStr") String searchStr);

    Page<SysDataDic> selectAll(Map<String,Object> params);

    int updateByPrimaryKey(SysDataDic record);
    
    List<Map<String,Object>> selectAllByCombox();

	List<Map<String, Object>> selectAllByComboxByCode(String code);
	
	List<SysDataDic> findAllCode(String typeid);
}
package cn.zealon.transaction.dao;

import cn.zealon.transaction.entity.TbAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @auther: Zealon
 * @Date: 2018-07-24 10:54
 */
@Mapper
public interface TbAccountMapper {

    @Select("select uid,account from tb_account where uid=#{uid}")
    TbAccount findById(String uid);

    @Update("update tb_account set account= #{account} where uid = #{uid}")
    int update(TbAccount ccount);
}

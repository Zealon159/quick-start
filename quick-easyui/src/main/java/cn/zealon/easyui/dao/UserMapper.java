package cn.zealon.easyui.dao;

import cn.zealon.easyui.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @auther: Zealon
 * @Date: 2018-06-27 15:44
 */
@Mapper
public interface UserMapper {

    @Select(" select id,userId,user_name userName,phone_Number phoneNumber,email_Address " +
            "emailAddress,created from org_user")
    Page<User> selectAll();
}

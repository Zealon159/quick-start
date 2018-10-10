package cn.zealon.dao;

import cn.zealon.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther: Zealon
 * @Date: 2018-10-10 15:34
 */
@Mapper
public interface UserDao {
    @Select("select * from user")
    List<User> selectAll();

    @Insert("insert into user(userName,age) values(#{userName},#{age})")
    int add(User user);
}

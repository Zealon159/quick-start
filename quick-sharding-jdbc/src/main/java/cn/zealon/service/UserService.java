package cn.zealon.service;

import cn.zealon.dao.UserDao;
import cn.zealon.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: Zealon
 * @Date: 2018-10-10 15:36
 */
@Service
public class UserService {

    @Autowired
    private UserDao dao;

    public List<User> selectAll(){
        return dao.selectAll();
    }

    public int add(User user){
        return dao.add(user);
    }
}

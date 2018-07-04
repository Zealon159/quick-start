package cn.zealon.service;

import cn.zealon.entity.UserBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: Zealon
 * @Date: 2018-07-04 15:15
 */
@Component
public class UserService {
    public UserBean getUser(String username) {
        // 没有此用户直接返回null
        Map<String, UserBean> userTable = getUserTable();
        if (! userTable.containsKey(username))
            return null;

        UserBean user =userTable.get(username);

        return user;
    }

    public Map<String,UserBean> getUserTable(){
        Map<String,UserBean> users = new HashMap<String,UserBean>();

        UserBean user = new UserBean();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setRole("admin");
        user.setPermission("admin");
        users.put("admin",user);

        user = new UserBean();
        user.setUsername("lily");
        user.setPassword("lily");
        user.setRole("user");
        user.setPermission("user");
        users.put("lily",user);

        return users;
    }
}

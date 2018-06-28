package cn.zealon.easyui.service;

import cn.zealon.common.util.PageConvertUtil;
import cn.zealon.easyui.dao.UserMapper;
import cn.zealon.easyui.entity.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 * @auther: Zealon
 * @Date: 2018-06-27 15:18
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<User> list = userMapper.selectAll();
        return PageConvertUtil.getGridJson(list);
    }
}

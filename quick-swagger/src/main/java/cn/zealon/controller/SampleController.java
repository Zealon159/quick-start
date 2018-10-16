package cn.zealon.controller;

import cn.zealon.entity.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @auther: Zealon
 * @Date: 2017-10-14 16:31
 */

@RestController
@RequestMapping("sample")
@Api(value="sample")
public class SampleController {

    @GetMapping("/getUserById")
    @ApiOperation(value = "通过ID查询User信息", httpMethod = "GET", notes = "暂无")
    public String getUserById(@ApiParam(required = true, name = "id", value = "用户ID") String id) {
        return "id:"+id;
    }

    @PostMapping("saveUser")
    @ApiOperation(value = "保存用户信息", httpMethod = "POST",response = User.class, notes = "暂无")
    public String saveUser(@ApiParam(required = true, name = "paramData", value = "用户信息") @RequestBody User paramData) {

        return paramData.toString();
    }

}

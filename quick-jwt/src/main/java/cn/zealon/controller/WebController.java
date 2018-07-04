package cn.zealon.controller;

import cn.zealon.common.exception.UnauthorizedException;
import cn.zealon.common.result.Result;
import cn.zealon.common.result.ResultUtil;
import cn.zealon.entity.UserBean;
import cn.zealon.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import cn.zealon.common.util.JWTUtil;

/**
 * @auther: Zealon
 * @Date: 2018-07-04 13:26
 */
@RestController
public class WebController {

    private static final Logger LOGGER = LogManager.getLogger(WebController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        UserBean userBean = userService.getUser(username);
        if (userBean.getPassword().equals(password)) {
            return ResultUtil.success(JWTUtil.sign(username, password));
        } else {
            throw new UnauthorizedException();
        }

    }

    @GetMapping("/article")
    public Result article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ResultUtil.success("You are already logged in");
        } else {
            return ResultUtil.success("You are guest");
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public Result requireAuth() {
        return ResultUtil.success("You are authenticated");
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public Result requireRole() {
        return ResultUtil.success("You are visiting require_role");
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public Result requirePermission() {
        return ResultUtil.success("You are visiting permission require edit,view");
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result unauthorized() {
        return ResultUtil.unAuthorized();
    }
}

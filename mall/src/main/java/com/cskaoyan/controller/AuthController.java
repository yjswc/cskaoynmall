package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.user.LoginToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 14:49
 * @Version: 1.0
 */
@RestController
@RequestMapping("admin/auth")
public class AuthController {
    @RequestMapping("login")
    public BaseRespVo login(@RequestBody LoginToken token) {
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setData("abc");
        respVo.setErrmsg("成功");
        return respVo;
    }

    @RequestMapping("info")
    public String info() {
        String resp = "{\"errno\":0,\"data\":{\"roles\":[\"超级管理员\"],\"name\":\"admin123\",\"perms\":[\"*\"],\"avatar\":\"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif\"},\"errmsg\":\"成功\"}";
        return resp;
    }

    @RequestMapping("logout")
    public BaseRespVo logout() {
        BaseRespVo<Object> respVo = new BaseRespVo<>();
        respVo.setErrno(0);
        respVo.setErrmsg("成功");
        return respVo;
    }

}

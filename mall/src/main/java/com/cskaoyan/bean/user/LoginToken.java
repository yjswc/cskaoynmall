package com.cskaoyan.bean.user;

import lombok.Data;

/**
 * @Author: Li Qing
 * @Create: 2020/4/23 21:10
 * @Version: 1.0
 * 登录令牌
 */
@Data
public class LoginToken {
    private String username;
    private String password;
}

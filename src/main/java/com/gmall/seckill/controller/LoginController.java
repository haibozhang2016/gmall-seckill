package com.gmall.seckill.controller;

import com.gmall.seckill.common.Const;
import com.gmall.seckill.po.User;
import com.gmall.seckill.dto.LoginParam;
import com.gmall.seckill.redis.RedisService;
import com.gmall.seckill.redis.UserKey;
import com.gmall.seckill.result.Result;
import com.gmall.seckill.service.UserService;
import com.gmall.seckill.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequestMapping("/user")
public class LoginController {

    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    @ResponseBody
    public Result<User> doLogin(HttpServletResponse response, HttpSession session , @Valid LoginParam loginParam) {
        Result<User> login = userService.login(loginParam);
        if (login.isSuccess()){
            CookieUtil.writeLoginToken(response,session.getId());
            redisService.set(UserKey.getByName , session.getId() ,login.getData(), Const.RedisCacheExtime.REDIS_SESSION_EXTIME );
        }
        return login;
    }

    @RequestMapping("/logout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtil.readLoginToken(request);
        CookieUtil.delLoginToken(request , response);
        redisService.del(UserKey.getByName , token);
        return "login";
    }
}
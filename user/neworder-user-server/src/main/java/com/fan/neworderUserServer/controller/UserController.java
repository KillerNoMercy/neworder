package com.fan.neworderUserServer.controller;

import com.fan.neworderUserCommon.bo.UserBO;
import com.fan.neworderUserServer.common.StringFormat;
import com.fan.neworderUserServer.common.UserRole;
import com.fan.neworderUserServer.model.User;
import com.fan.neworderUserServer.service.UserService;
import com.fan.neworderUserServer.vo.ResultVO;
import com.fan.neworderUserServer.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    private StringRedisTemplate stringRedisTemplate;

    public UserController(UserService userService, StringRedisTemplate stringRedisTemplate) {
        this.userService = userService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping("buyerLogin")
    public ResultVO buyerLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpServletResponse response) {
        // 用户校验
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
            return ResultVO.fail("账号、密码不能为空");
        User user = userService.login(username, password);
        if(user == null || user.getRole() != UserRole.BUYER)
            return ResultVO.fail("该token不存在");
        // 登录成功，生成token，写入cookie并记录reids
        String token = UUID.randomUUID().toString().substring(0, 10);
        Cookie cookie = new Cookie("token", token);
        cookie.setComment("用户登录标识");
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
        stringRedisTemplate.opsForValue().set(String.format(StringFormat.TOKEN, user.getId()), token);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultVO.success(userVO);
    }

    @PostMapping("checkToken")
    public UserBO checkToken(@RequestBody String token) {
        return userService.checkToken(token);
    }
}

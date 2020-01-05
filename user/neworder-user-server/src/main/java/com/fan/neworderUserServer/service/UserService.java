package com.fan.neworderUserServer.service;

import com.fan.neworderUserCommon.bo.UserBO;
import com.fan.neworderUserServer.model.User;
import com.fan.neworderUserServer.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Auther: zhengfan
 * Date: 2020/1/3
 */
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public UserBO checkToken(String token) {
        User byToken = userRepository.findByToken(token);
        if(byToken == null)
            return null;
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(byToken, userBO);
        return userBO;
    }
}

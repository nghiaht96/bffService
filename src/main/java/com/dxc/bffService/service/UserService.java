package com.dxc.bffService.service;

import com.dxc.bffService.api.UserApi;
import com.dxc.bffService.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserApi userApi;

    public User getUserByUsername(String username){
        return userApi.getUserByUsername(username);
    }

    public String deleteUser(String username){
        return userApi.deleteUser(username);
    }

    public String loginUser(String username, String password){
        return userApi.loginUser(username, password);
    }

    public String logoutUser(){
        return userApi.logoutUser();
    }

    public String upsertUseR(User user){
        return userApi.upsertUser(user);
    }
}

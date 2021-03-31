package com.example.SpringRest.service;

import com.example.SpringRest.model.User;

public interface UserSevice {
    void save(User user);

    User findByUsername(String username);

}

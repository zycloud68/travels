package com.cs.travels.service;

import com.cs.travels.entity.User;

public interface UserService {
    void register(User user);

    User login(User user);
}

package com.test.auth.service;

import com.test.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}

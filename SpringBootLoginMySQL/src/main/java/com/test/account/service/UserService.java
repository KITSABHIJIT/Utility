package com.test.account.service;

import com.test.account.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}

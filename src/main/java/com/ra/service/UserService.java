package com.ra.service;

import com.ra.entity.User;
import com.ra.util.exception.UserException;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User create(User user) throws UserException;

}

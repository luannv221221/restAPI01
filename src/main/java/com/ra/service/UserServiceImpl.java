package com.ra.service;

import com.ra.entity.User;
import com.ra.repository.UserRepository;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) throws UserException {
        // kiểm tra có cái email chưa nhé
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserException(user.getEmail()+" đã tồn tại");
        }
        return userRepository.save(user);
    }
}

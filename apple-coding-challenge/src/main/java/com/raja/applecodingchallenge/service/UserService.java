package com.raja.applecodingchallenge.service;

import java.util.List;

import com.raja.applecodingchallenge.model.User;

public interface UserService {
    User save(User user);

    User findByUsername(String username);

    List<String> findUsers(List<Long> idList);

    List<User> findAllUsers();
    
    String calculateMaxSum(String treeData);
}
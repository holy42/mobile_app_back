package com.app.mobile.service;

import com.app.mobile.dto.UserDto;
import com.app.mobile.model.User;

import java.util.List;

public interface UserService {
    public User insertUser(UserDto userDTO);
    public List<User> getAllUser();
    public User updateUser(User user);
    public String deleteUser(int id);
    public List<User> getUserSearch(String recherche);
}

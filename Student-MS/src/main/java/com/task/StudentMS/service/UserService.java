package com.task.StudentMS.service;

import com.task.StudentMS.entity.EntityUser;
import com.task.StudentMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public String addUser(EntityUser user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User added";
    }

    public String deleteUser(String username){
        EntityUser user = userRepository.findByUsername(username);
        userRepository.delete(user);
        return "User deleted";
    }
}

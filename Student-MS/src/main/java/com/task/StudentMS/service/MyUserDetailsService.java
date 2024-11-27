package com.task.StudentMS.service;

import com.task.StudentMS.entity.EntityUser;
import com.task.StudentMS.repository.UserRepository;
import com.task.StudentMS.config.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        EntityUser user = userRepository.findByUsername(username);
//        System.out.println("here");
        if (user == null) {
//            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

//        System.out.println(user.toString());

        return new UserDetailsImp(user);
    }
}

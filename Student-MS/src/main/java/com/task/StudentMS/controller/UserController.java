package com.task.StudentMS.controller;

import com.task.StudentMS.entity.EntityUser;
import com.task.StudentMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("create")
    public String addUser(@RequestBody EntityUser user){
        return userService.addUser(user);
    }

    @DeleteMapping("delete")
    public String deleteUser(@RequestParam String username){
        return userService.deleteUser(username);
    }

    @GetMapping("verify")
    public String verifyUser(){
        return "Valid user";
    }

}

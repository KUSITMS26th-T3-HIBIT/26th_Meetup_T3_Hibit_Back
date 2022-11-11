package com.hibit.kusitms26tht3hibitback.controller;

import com.hibit.kusitms26tht3hibitback.domain.User;
import com.hibit.kusitms26tht3hibitback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/sign-up")
    public User register(@RequestBody User user) {

        return userService.insertUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/sign-up/{id}")
    public Map<String, Object> verifyEmail(@PathVariable String id){
        Map<String, Object> response = new HashMap<>();
        if(userService.existId(id)==false){
            response.put("result", "사용 가능한 아이디입니다..");
            response.put("id", id);
        }
        else{
            response.put("result", "FAIL");
            response.put("reason", "이미 존재하는 아이디입니다..");
        }
        return response;
    }
}
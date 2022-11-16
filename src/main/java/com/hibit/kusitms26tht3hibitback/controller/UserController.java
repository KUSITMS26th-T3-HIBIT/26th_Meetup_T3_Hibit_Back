package com.hibit.kusitms26tht3hibitback.controller;

import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.global.jwt.JwtTokenProvider;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import com.hibit.kusitms26tht3hibitback.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Tag(name="user", description = "회원가입/로그인 API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "signup", description = "회원가입")
    @Parameters({@Parameter(name = "id",description="아이디",example = "arin123"),
            @Parameter(name = "password",description="비밀번호",example = "1234"),
            @Parameter(name = "nickname",description="닉네임",example = "이아린"),
            @Parameter(name = "phone_number",description="전화번호",example = "01011112222"),
            @Parameter(name = "age",description="나이",example = "24"),
            @Parameter(name = "gender",description="성별",example = "True"),
            @Parameter(name = "home",description="주소",example = "경기도"),
            @Parameter(name = "introduce",description="자기 소개",example = "안녕하세요.")})
    @RequestMapping(method = RequestMethod.POST, path = "/sign-up")
    public Users register(@RequestBody Users users) {

        return userService.insertUser(users);
    }

    @Operation(summary = "회원가입 - 아이디 중복 확인")
    @RequestMapping(method = RequestMethod.GET, path = "/sign-up/{id}")
    public Map<String, Object> verifyEmail(@Parameter(name = "id", description = "user 의 id", in = ParameterIn.PATH) @PathVariable String id){
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

    @Operation(summary = "회원가입 - 닉네임 중복 확인")
    @RequestMapping(method = RequestMethod.GET, path = "/sign-up/exists/{nickname}")
    public Map<String, Object> verifyNickname(@Parameter(name = "nickname", description = "user의 nickname", in = ParameterIn.PATH) @PathVariable String nickname){
        Map<String, Object> response = new HashMap<>();
        if(userService.existNickname(nickname)==false){
            response.put("result", "사용 가능한 닉네임입니다.");
            response.put("nickname", nickname);
        }
        else{
            response.put("result", "FAIL");
            response.put("reason", "이미 존재하는 닉네임입니다.");
        }
        return response;
    }

    @Operation(summary = "signin", description = "로그인")
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user){
        log.info("user id = {}", user.get("id"));
        Users member = userRepository.findById(user.get("id"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디입니다."));

        if(member==null || !member.getPassword().equals(user.get("password"))){
            return String.valueOf(new IllegalArgumentException("비밀번호가 일치하지 않습니다."));
        }

        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }


}
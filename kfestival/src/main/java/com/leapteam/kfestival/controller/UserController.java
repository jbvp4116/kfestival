package com.leapteam.kfestival.controller;

import com.leapteam.kfestival.dto.UserDTO;
import com.leapteam.kfestival.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController
{
    //생성자
    private final UserService userService;

    //회원가입 페이지 출력
    @GetMapping("/signup")
    public String registForm()
    {
        return "signup";
    }

    //register 페이지에서 Controller로 param 받아오기
    @PostMapping("/signup")
    public String regist(@ModelAttribute UserDTO userDTO)
    {
        //println은 정상 작동 체크용
        System.out.println("UserController.registration Complete");
        //이게 값 받아오는 코드, DTO로 넘겨주는 코드예요
        //Controller -> DTO -> Service -> Entity 전환 -> Repository
        userService.save(userDTO);

        return "login";
    }

    @GetMapping("/login")
    public String loginForm()
    {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session)
    {
        UserDTO loginResult = userService.login(userDTO);
        if (loginResult != null) //로그인 성공
        {
            session.setAttribute("loginEmail", loginResult.getUserEmail());
            return "home";
        }

        else //로그인 실패
        {
            return "login";
        }
    }
}

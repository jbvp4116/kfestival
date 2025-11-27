package com.leapteam.kfestival.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String indexpage()
    {
        return "home";
    }

    @GetMapping("/home")
    public String homepage()
    {
        return "home";
    }

    @GetMapping("/festival")
    public String festival()
    {
        return "festival";
    }

    @GetMapping("/community")
    public String community()
    {
        return "community";
    }
}

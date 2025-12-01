package com.leapteam.kfestival.Controller;

import com.leapteam.kfestival.entity.FestivalEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.leapteam.kfestival.service.FestivalService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController
{
    @GetMapping("/")
    public String indexpage()
    {
        return "home";
    }

    private final FestivalService festivalService;

    @GetMapping("/festival")
    public String showFestivals(Model model)
    {
        List<FestivalEntity> festivals = festivalService.loadFestivalData();
        model.addAttribute("festivals", festivals);
        return "festival";
    }

    @GetMapping("/home")
    public String homepage()
    {
        return "home";
    }

    @GetMapping("/community")
    public String community()
    {
        return "community";
    }
}

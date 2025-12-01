package com.leapteam.kfestival.Controller;

import com.leapteam.kfestival.entity.FestivalEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.leapteam.kfestival.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController
{
    private final FestivalService festivalService;

    @GetMapping({"/", "/home"})
    public String homepage(Model model) {
        List<FestivalEntity> allFestivals = festivalService.loadFestivalData();

        List<FestivalEntity> recommendedFestivals = allFestivals.stream()
                .limit(3)
                .toList();

        List<FestivalEntity> upcomingFestivals = allFestivals.stream()
                .skip(Math.max(0, allFestivals.size() - 3))
                .toList();

        model.addAttribute("recommendedFestivals", recommendedFestivals);
        model.addAttribute("upcomingFestivals", upcomingFestivals);

        return "home";
    }

    @GetMapping("/festival")
    public String showFestivals()
    {
        return "festival";
    }

    @GetMapping("/festival/{id}")
    public String festivalDetail(@PathVariable Long id, Model model) {
        FestivalEntity festival = festivalService.findById(id);
        model.addAttribute("festival", festival);
        return "festival";
    }

    @GetMapping("/community")
    public String community()
    {
        return "community";
    }
}

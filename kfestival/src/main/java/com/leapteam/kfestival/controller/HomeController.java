package com.leapteam.kfestival.controller;

import com.leapteam.kfestival.entity.FestivalEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.leapteam.kfestival.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class HomeController
{
    private final FestivalService festivalService;

    @GetMapping({"/", "/home"})
    public String homepage(Model model) {
        List<FestivalEntity> festivals = festivalService.getAllFestivals();

        // 추천 축제: 이미지 있는 최신 3개
        List<FestivalEntity> recommended = festivals.stream()
                .filter(f -> f.getImageUrl() != null && !f.getImageUrl().isEmpty())
                .sorted((a, b) -> b.getCreatedTime().compareTo(a.getCreatedTime()))
                .limit(3)
                .toList();

        // 임박 축제: eventStartDate 기준 오름차순
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        List<FestivalEntity> upcoming = festivals.stream()
                .filter(f -> f.getEventStartDate() != null && !f.getEventStartDate().isEmpty() && !f.getImageUrl().isEmpty())
                .sorted(Comparator.comparing(f -> LocalDate.parse(f.getEventStartDate(), formatter)))
                .limit(3)
                .toList();

        // 배너용: 이미지 있는 축제 중 랜덤 선택
        List<FestivalEntity> bannerFestivals = festivals.stream()
                .filter(f -> f.getImageUrl() != null && !f.getImageUrl().isEmpty())
                .toList();

        model.addAttribute("recommendedFestivals", recommended);
        model.addAttribute("upcomingFestivals", upcoming);
        model.addAttribute("bannerFestivals", bannerFestivals);

        return "home";
    }

    @GetMapping("/festival_search")
    public String showFestivals(@RequestParam(required = false) String location, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, Model model)
    {
        List<FestivalEntity> searchResults = festivalService.search(location, date);
        model.addAttribute("searchResults", searchResults);
        return "festival_search";
    }

    @GetMapping("/festival/{id}")
    public String festivalDetail(@PathVariable Long id, Model model) {
        List<FestivalEntity> festivals = festivalService.getAllFestivals();

        List<FestivalEntity> bannerFestivals = festivals.stream()
                .filter(f -> f.getImageUrl() != null && !f.getImageUrl().isEmpty())
                .toList();

        model.addAttribute("bannerFestivals", bannerFestivals);
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

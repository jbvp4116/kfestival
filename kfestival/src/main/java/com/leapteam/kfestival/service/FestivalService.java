package com.leapteam.kfestival.service;

import com.leapteam.kfestival.dto.FestivalResponseDTO;
import com.leapteam.kfestival.entity.FestivalEntity;
import com.leapteam.kfestival.repositiory.FestivalRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FestivalService {

    private final RestTemplate restTemplate;
    private final FestivalRepository festivalRepository;

    private final String API_URL = "https://apis.data.go.kr/B551011/KorService2/searchFestival2?numOfRows=100&pageNo=1&MobileOS=WEB&MobileApp=KFestival&_type=json&eventStartDate=20251207&serviceKey=5a3e607b685cd79cb54c028b08f26630da1031036743861dab68d94f1bd558e7";

    @Transactional
    public List<FestivalEntity> loadFestivalData() {
        List<FestivalEntity> festivals = fetchFromApi();
        festivalRepository.saveAll(festivals);
        return festivals;
    }

    public List<FestivalEntity> getAllFestivals() {
        return festivalRepository.findAll();
    }

    private List<FestivalEntity> fetchFromApi() {

        FestivalResponseDTO responseDTO =
                restTemplate.getForObject(API_URL, FestivalResponseDTO.class);

        if (responseDTO == null || responseDTO.getResponse() == null ||
                responseDTO.getResponse().getBody() == null ||
                responseDTO.getResponse().getBody().getItems() == null ||
                responseDTO.getResponse().getBody().getItems().getItem() == null) {
            return List.of();
        }

        return responseDTO.getResponse().getBody().getItems().getItem().stream()
                .map(item -> {
                    FestivalEntity e = new FestivalEntity();
                    e.setContentId(item.getContentid());
                    e.setTitle(item.getTitle());
                    e.setAddr1(item.getAddr1());
                    e.setAddr2(item.getAddr2());
                    e.setZipcode(item.getZipcode());
                    e.setCategory1(item.getCat1());
                    e.setCategory2(item.getCat2());
                    e.setCategory3(item.getCat3());
                    e.setImageUrl(item.getFirstimage());
                    e.setImageUrl2(item.getFirstimage2());
                    e.setTel(item.getTel());
                    e.setCreatedTime(item.getCreatedtime());
                    e.setEventStartDate(item.getEventstartdate());
                    e.setEventEndDate(item.getEventenddate());
                    return e;
                }).collect(Collectors.toList());
    }

    public FestivalEntity findById(Long id) {
        return festivalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 축제가 존재하지 않습니다. id=" + id));
    }

    @PostConstruct
    public void init() {
        loadFestivalData(); // 서버 시작 시 데이터 로딩
    }
}
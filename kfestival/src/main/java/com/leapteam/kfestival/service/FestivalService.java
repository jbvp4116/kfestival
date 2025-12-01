package com.leapteam.kfestival.service;

import com.leapteam.kfestival.dto.FestivalResponseDTO;
import com.leapteam.kfestival.entity.FestivalEntity;
import com.leapteam.kfestival.repositiory.FestivalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FestivalService {
    private final RestTemplate restTemplate;
    private final FestivalRepository festivalRepository;

    //일단 구현 편의상 URL 통채로 넣었습니다
    //종료 후 시간 남으면 처리할게요
    private final String API_URL = "https://apis.data.go.kr/6300000/openapi2022/festv/getfestv?serviceKey=5a3e607b685cd79cb54c028b08f26630da1031036743861dab68d94f1bd558e7&pageNo=1&numOfRows=100&resultType=json";

    @Transactional
    public List<FestivalEntity> loadFestivalData()
    {
        List<FestivalEntity> festivals = fetchFromApi();
        festivalRepository.saveAll(festivals);
        return festivals;
    }

    public List<FestivalEntity> getAllFestivals()
    {
        return festivalRepository.findAll();
    }

    private List<FestivalEntity> fetchFromApi() {
        FestivalResponseDTO responseDTO =
                restTemplate.getForObject(API_URL, FestivalResponseDTO.class);

        if (responseDTO == null ||
                responseDTO.getResponse() == null ||
                responseDTO.getResponse().getBody() == null ||
                responseDTO.getResponse().getBody().getItems() == null) {
            return List.of();
        }

        return responseDTO.getResponse().getBody().getItems().stream()
                .map(item -> {
                    FestivalEntity e = new FestivalEntity();
                    e.setFestvNm(item.getFestvNm());
                    e.setFestvSumm(item.getFestvSumm());
                    e.setFestvTpic(item.getFestvTpic());
                    e.setFestvPrid(item.getFestvPrid());
                    e.setFestvPlcNm(item.getFestvPlcNm());
                    e.setFestvHostNm(item.getFestvHostNm());
                    e.setFestvZip(item.getFestvZip());
                    e.setFestvAddr(item.getFestvAddr());
                    e.setFestvDtlAddr(item.getFestvDtlAddr());
                    e.setRefadNo(item.getRefadNo());
                    e.setHmpgAddr(item.getHmpgAddr());
                    return e;
                })
                .toList();
    }

    public FestivalEntity findById(Long id)
    {
        return festivalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 축제가 존재하지 않습니다. id=" + id));
    }
}

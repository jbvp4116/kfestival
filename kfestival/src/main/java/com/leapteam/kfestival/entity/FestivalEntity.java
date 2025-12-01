package com.leapteam.kfestival.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FestivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String festvNm;
    @Column(length = 1000)
    private String festvSumm;
    private String festvTpic;
    private String festvPrid;
    private String festvPlcNm;
    private String festvHostNm;
    private String festvZip;
    private String festvAddr;
    private String festvDtlAddr;
    private String refadNo;
    private String hmpgAddr;
}

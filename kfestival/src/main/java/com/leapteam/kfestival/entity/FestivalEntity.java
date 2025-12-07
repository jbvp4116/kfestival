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

    private String contentId;
    private String title;

    @Column(length = 1000)
    private String summary;

    private String addr1;
    private String addr2;
    private String zipcode;
    private String category1;
    private String category2;
    private String category3;
    private String imageUrl;
    private String imageUrl2;

    @Column(length = 1000)
    private String tel;
    private String createdTime;
    private String eventStartDate;
    private String eventEndDate;

    @Transient
    private String description;
}
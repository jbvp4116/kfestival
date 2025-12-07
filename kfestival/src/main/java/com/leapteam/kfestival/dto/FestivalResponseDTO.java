package com.leapteam.kfestival.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FestivalResponseDTO {

    private Response response;

    @Getter
    @Setter
    public static class Response {
        private Header header;
        private Body body;
    }

    @Getter
    @Setter
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @Setter
    public static class Body {
        private int totalCount;
        private Items items;
    }

    @Getter
    @Setter
    public static class Items {
        private List<FestivalItem> item;
    }

    @Getter
    @Setter
    public static class FestivalItem {
        private String title;
        private String addr1;
        private String addr2;
        private String zipcode;
        private String cat1;
        private String cat2;
        private String cat3;
        private String contentid;
        private String contenttypeid;
        private String createdtime;
        private String eventstartdate;
        private String eventenddate;
        private String firstimage;
        private String firstimage2;
        private String tel;
    }
}
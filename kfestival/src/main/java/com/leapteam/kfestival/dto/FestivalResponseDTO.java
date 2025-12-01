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
    public static class Response
    {
        private Header header;
        private Body body;
    }

    @Getter
    @Setter
    public static class Header
    {
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @Setter
    public static class Body
    {
        private int totalCount;
        private List<FestivalItem> items;
    }

    @Getter
    @Setter
    public static class FestivalItem
    {
        private String festvNm;
        private String festvSumm;
        private String festvTpic;
        private String festvPrid;
        private String festvPlcNm;
        private String festvHostNm;
        private String svorgnNm;
        private String festvZip;
        private String festvAddr;
        private String festvDtlAddr;
        private String refadNo;
        private String hmpgAddr;
    }
}

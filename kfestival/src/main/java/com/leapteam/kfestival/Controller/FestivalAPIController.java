package com.leapteam.kfestival.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//아직 검증 안 됐어요

@Controller
public class FestivalAPIController {

    public static final String ServiceKey = "5a3e607b685cd79cb54c028b08f26630da1031036743861dab68d94f1bd558e7";

    @ResponseBody
    public String DaejeonFestival() throws IOException
    {
        String Daejeonurl = "https://apis.data.go.kr/6300000/openapi2022/festv/getfestv?serviceKey=";
        Daejeonurl += ServiceKey;
        Daejeonurl += "&pageNo=1&numOfRows=100";
        //대전 축제 API url
        //그와 별개로 축제가 없음

        String Busanurl = "http://apis.data.go.kr/6260000/FestivalService/getFestivalKr?serviceKey=";
        Busanurl += ServiceKey;
        Busanurl += "&pageNo=1&numOfRows=100&resultType=json";

        String Ulsanurl = "http://apis.data.go.kr/6310000/ulsanfestival/getUlsanfestivalList?serviceKey=";
        Ulsanurl += ServiceKey;

        String Sejongurl = "http://apis.data.go.kr/5690000/sjFestival/sj_00000360?serviceKey=";
        Sejongurl += ServiceKey;
        Sejongurl += "&padeIndex=1&pageUnit=100";
        URL requestUrl = new URL(Daejeonurl);
        HttpURLConnection urlConn = (HttpURLConnection) requestUrl.openConnection();
        urlConn.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

        String response = "";
        String line;
        while ((line = br.readLine()) != null)
        {
            response += line;
        }

        br.close();
        urlConn.disconnect();

        System.out.println(response);

        return response;
    }
}

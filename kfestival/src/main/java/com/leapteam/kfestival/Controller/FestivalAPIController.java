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
    public static final String DaejeonKey = "5a3e607b685cd79cb54c028b08f26630da1031036743861dab68d94f1bd558e7";

    @ResponseBody
    public String DaejeonFestival() throws IOException
    {
        String url = "https://apis.data.go.kr/6300000/openapi2022/festv";
        url += "?serviceKey=" + DaejeonKey;
        url += "&pageNo=1";
        url += "&numOfRows=10";

        URL requestUrl = new URL(url);
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

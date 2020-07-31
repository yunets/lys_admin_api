package com.lys.lys_admin_api.common.utils;

import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 * @Auther: liuysh
 * @Date: 2020/7/17 16:37
 * @Description:
 */
public class RestTemplateUtils {
    public static void main(String[] args) {


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String content = "{\"currentPage\": 0,\"pageSize\": 2}";
        HttpEntity<String> request = new HttpEntity<>(content, headers);
        String url = "http://localhost:8080/sys/user/selectAll1";
        ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);
        String body = postForEntity.getBody();
        System.out.println(body);


    }
}

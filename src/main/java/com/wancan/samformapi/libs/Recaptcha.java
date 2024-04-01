package com.wancan.samformapi.libs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;

@Component
public class Recaptcha {

    @Value("${recaptcha.token.secret}")
    private String recaptchSecret;


    public boolean verifyRecaptch(String token) throws Exception{
        try{

            String requestBody = String.format("{\"secret\":\"%s\", \"response\":\"%s\"}", recaptchSecret, token);
            System.out.println("BODY"+requestBody);
            HttpEntity<String> request = new HttpEntity<>(requestBody);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.exchange(
                    "https://www.google.com/recaptcha/api/siteverify",
                    HttpMethod.POST,
                    request,
                    String.class
            );
            HttpStatusCode statusCode = response.getStatusCode();

            System.out.println("Status Code:"+ statusCode);
            System.out.println("DATA:"+ response.getBody());

            return true;
        }catch (Exception err){
            throw err;
        }
    }

}

package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.gif.GetGifResponseDto;
import com.example.mycurrencycomparator.exception.GifsServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@Profile("profile2")
public class GifsServiceImpl implements GifsService {

    @Value("${service.gifs.url}")
    private String url;

    @Value("${service.gifs.apiKey}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<GetGifResponseDto> getGif(String qWord) {

        Integer offset = new Random().nextInt(1) + 1;
        String p1 = "api_key=" + apiKey;
        String p2 = "q=" + qWord;
        String p3 = "limit=" + 1;
        String p4 = "offset=" + offset.toString();
        String p5 = "bundle=" + "messaging_non_clips";
        String request = url + "?" + p1 + "&" + p2 + "&" + p3 + "&" + p4 + "&" + p5;

        ResponseEntity<GetGifResponseDto> response;
        try {
            response = restTemplate.getForEntity(request, GetGifResponseDto.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new GifsServiceException();
        }

        return response;
    }
}

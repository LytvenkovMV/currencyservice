package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.client.GifClient;
import com.example.mycurrencycomparator.dto.gif.GetGifResponseDto;
import com.example.mycurrencycomparator.exception.GifsServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Profile("profile1")
public class GifsServiceFeignImpl implements GifsService {

    @Value("${service.gifs.apiKey}")
    private String apiKey;

    @Autowired
    GifClient gifClient;

    @Override
    public ResponseEntity<GetGifResponseDto> getGif(String qWord) {

        Integer offset = new Random().nextInt(99) + 1;

        ResponseEntity<GetGifResponseDto> response;
        try {
            response = gifClient.getGif(apiKey, qWord, "1", offset.toString(), "messaging_non_clips");
        } catch (Exception e) {
            e.printStackTrace();
            throw new GifsServiceException();
        }

        return ResponseEntity.ok(response.getBody());
    }
}

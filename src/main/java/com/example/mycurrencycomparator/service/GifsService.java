package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.gif.GetGifResponseDto;
import org.springframework.http.ResponseEntity;

public interface GifsService {

    ResponseEntity<GetGifResponseDto> getGif(String qWord);
}

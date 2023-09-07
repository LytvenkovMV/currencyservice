package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.comparator.MyComparatorResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface MyComparatorService {

    ResponseEntity<MyComparatorResponseDto> compare(Optional<String> comparedCurrency) ;
}

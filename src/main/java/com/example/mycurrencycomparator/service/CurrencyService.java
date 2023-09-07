package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import org.springframework.http.ResponseEntity;

public interface CurrencyService {

    ResponseEntity<CompareCurrencyResponseDto> getCompareResult(String comparedCurrency);
}

package com.example.mycurrencycomparator.dto.currencyrate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchApiResponseDto {

    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    Map<String,Double> rates;
}

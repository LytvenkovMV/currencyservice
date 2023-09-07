package com.example.mycurrencycomparator.dto.currencyrate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompareCurrencyResponseDto {

    private String base_currency;
    private String compared_currency;
    private String compare_result;
    private List<RateDataDto> rate_data;
}

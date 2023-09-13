package com.example.mycurrencycomparator.dto.currencyrate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompareCurrencyResponseDto {

    private String baseCurrency;

    private String comparedCurrency;

    private String compareResult;

    private List<RateDataDto> rateData;
}

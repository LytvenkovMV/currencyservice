package com.example.mycurrencycomparator.dto.currencyrate;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDataDto {

    @ApiModelProperty(value = "Дата", required = true)
    private String date;

    @ApiModelProperty(value = "Курс", required = true)
    private double rate;
}

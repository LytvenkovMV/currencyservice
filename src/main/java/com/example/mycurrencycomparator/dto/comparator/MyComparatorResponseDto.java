package com.example.mycurrencycomparator.dto.comparator;

import com.example.mycurrencycomparator.dto.currencyrate.RateDataDto;
import com.example.mycurrencycomparator.dto.gif.AnalyticsDto;
import com.example.mycurrencycomparator.dto.gif.ImagesDto;
import com.example.mycurrencycomparator.dto.gif.UserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Результат сравнения курса валюты, заданной в запросе, и базовой валюты")
public class MyComparatorResponseDto {

    @ApiModelProperty(value = "Валюта, заданная в запросе", required = true)
    private String comparedCurrency;

    @ApiModelProperty(value = "Базовая валюта", required = true)
    private String baseCurrency;

    @ApiModelProperty(value = "Результат сравнения", required = true)
    private String compareResult;

    @ApiModelProperty(value = "Информация о курсах валюты за разные даты", required = true)
    private List<RateDataDto> rateData;

    @ApiModelProperty(value = "Название гифки", required = true)
    private String gifTitle;

    @ApiModelProperty(value = "Ссылки на гифки", required = true)
    private ImagesDto images;
}

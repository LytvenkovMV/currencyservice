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
    private String compared_currency;

    @ApiModelProperty(value = "Базовая валюта", required = true)
    private String base_currency;

    @ApiModelProperty(value = "Результат сравнения", required = true)
    private String compare_result;

    @ApiModelProperty(value = "Информация о курсах валюты за разные даты", required = true)
    private List<RateDataDto> rate_data;

    @ApiModelProperty(value = "Название гифки", required = true)
    private String gif_title;

    @ApiModelProperty(value = "Ссылки на гифки", required = true)
    private ImagesDto images;
}

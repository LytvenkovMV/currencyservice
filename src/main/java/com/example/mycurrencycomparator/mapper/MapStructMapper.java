package com.example.mycurrencycomparator.mapper;

import com.example.mycurrencycomparator.dto.comparator.MyComparatorResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.RateDataDto;
import com.example.mycurrencycomparator.dto.gif.GetGifResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.ArrayList;

@Mapper
public interface MapStructMapper {

    @Mapping(target = "baseCurrency", source = "latestResponseDto.base")
    @Mapping(target = "compareResult", expression = "java(getCompareResult(comparedCurrency, histResponseDto, latestResponseDto))")
    @Mapping(target = "rateData", expression = "java(getRateData(comparedCurrency, localDateToday, localDateYesterday, histResponseDto, latestResponseDto))")
    CompareCurrencyResponseDto fromComparedCurrencyAndDatesAndExchApiResponseDtos(String comparedCurrency
            , LocalDate localDateToday
            , LocalDate localDateYesterday
            , ExchApiResponseDto histResponseDto
            , ExchApiResponseDto latestResponseDto);


    @Mapping(target = "gifTitle", expression = "java(getGifResponseDto.getData().get(0).getTitle())")
    @Mapping(target = "images", expression = "java(getGifResponseDto.getData().get(0).getImages())")
    MyComparatorResponseDto fromCompareCurrencyAndGetGifResponseDto(CompareCurrencyResponseDto compareCurrencyResponseDto
            , GetGifResponseDto getGifResponseDto);


    RateDataDto fromDateAndRate(String date, Double rate);


    default String getCompareResult(String comparedCurrency, ExchApiResponseDto histResponseDto
            , ExchApiResponseDto latestResponseDto) {

        Double histRate = histResponseDto.getRates().get(comparedCurrency);
        Double latestRate = latestResponseDto.getRates().get(comparedCurrency);

        if (latestRate <= histRate) return  "rich";
        return "broke";
    }


    default ArrayList<RateDataDto> getRateData(String comparedCurrency
            , LocalDate localDateToday
            , LocalDate localDateYesterday
            , ExchApiResponseDto histResponseDto
            , ExchApiResponseDto latestResponseDto) {

        RateDataDto histRateData = fromDateAndRate(localDateYesterday.toString()
                , histResponseDto.getRates().get(comparedCurrency));
        RateDataDto latestRateData = fromDateAndRate(localDateToday.toString()
                , latestResponseDto.getRates().get(comparedCurrency));

        ArrayList<RateDataDto> rateData = new ArrayList<>();
        rateData.add(latestRateData);
        rateData.add(histRateData);

        return rateData;
    }
}

package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.comparator.MyComparatorResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.RateDataDto;
import com.example.mycurrencycomparator.dto.gif.GetGifResponseDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;

public class DtoMapper {

    protected static CompareCurrencyResponseDto getCompareCurrencyResponseDto(
            String comparedCurrency
            , LocalDate localDateToday
            , LocalDate localDateYesterday
            , ExchApiResponseDto histResponseDto
            , ExchApiResponseDto latestResponseDto) {

        Double histRate = histResponseDto.getRates().get(comparedCurrency);
        Double latestRate = latestResponseDto.getRates().get(comparedCurrency);

        String compareResult = "broke";
        if (latestRate <= histRate) compareResult = "rich";

        RateDataDto histRateData = new RateDataDto();
        histRateData.setDate(localDateYesterday.toString());
        histRateData.setRate(histRate);

        RateDataDto latestRateData = new RateDataDto();
        latestRateData.setDate(localDateToday.toString());
        latestRateData.setRate(latestRate);

        ArrayList<RateDataDto> rateData = new ArrayList<>();
        rateData.add(latestRateData);
        rateData.add(histRateData);

        CompareCurrencyResponseDto compareCurrencyResponseDto = new CompareCurrencyResponseDto();
        compareCurrencyResponseDto.setBase_currency(latestResponseDto.getBase());
        compareCurrencyResponseDto.setCompared_currency(comparedCurrency);
        compareCurrencyResponseDto.setRate_data(rateData);
        compareCurrencyResponseDto.setCompare_result(compareResult);

        return compareCurrencyResponseDto;
    }


    protected static MyComparatorResponseDto getMyComparatorResponseDto(
            CompareCurrencyResponseDto compareCurrencyResponseDto
            , GetGifResponseDto getGifResponseDto) {

        MyComparatorResponseDto myComparatorResponseDto = new MyComparatorResponseDto();
        myComparatorResponseDto.setCompared_currency(compareCurrencyResponseDto.getCompared_currency());
        myComparatorResponseDto.setBase_currency(compareCurrencyResponseDto.getBase_currency());
        myComparatorResponseDto.setCompare_result(compareCurrencyResponseDto.getCompare_result());
        myComparatorResponseDto.setRate_data(compareCurrencyResponseDto.getRate_data());
        myComparatorResponseDto.setGif_title(getGifResponseDto.getData().get(0).getTitle());
        myComparatorResponseDto.setImages(getGifResponseDto.getData().get(0).getImages());

        return myComparatorResponseDto;
    }
}

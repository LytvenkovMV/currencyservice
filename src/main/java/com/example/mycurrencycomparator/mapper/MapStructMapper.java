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


//    @Mapping(target = "groupName", source = "group.name")
//    @Mapping(target = "groupId", source = "group.id")
//
//    @Mapping(target = "name", source = "addStudentRequestDto.name")
//    @Mapping(target = "registrationDate", expression = "java(java.time.LocalDate.now())")
//    @Mapping(target = "id", constant = "0")
//    @Mapping(target = "group", source = "group")




    @Mapping(target = "baseCurrency", source = "latestResponseDto.base")
    CompareCurrencyResponseDto fromComparedCurrencyAndDatesAndExchApiResponseDtos(
            String comparedCurrency
            , LocalDate localDateToday
            , LocalDate localDateYesterday
            , ExchApiResponseDto histResponseDto
            , ExchApiResponseDto latestResponseDto);






//    protected static CompareCurrencyResponseDto getCompareCurrencyResponseDto(
//            String comparedCurrency
//            , LocalDate localDateToday
//            , LocalDate localDateYesterday
//            , ExchApiResponseDto histResponseDto
//            , ExchApiResponseDto latestResponseDto) {
//
//        Double histRate = histResponseDto.getRates().get(comparedCurrency);
//        Double latestRate = latestResponseDto.getRates().get(comparedCurrency);
//
//        String compareResult = "broke";
//        if (latestRate <= histRate) compareResult = "rich";
//
//        RateDataDto histRateData = new RateDataDto();
//        histRateData.setDate(localDateYesterday.toString());
//        histRateData.setRate(histRate);
//
//        RateDataDto latestRateData = new RateDataDto();
//        latestRateData.setDate(localDateToday.toString());
//        latestRateData.setRate(latestRate);
//
//        ArrayList<RateDataDto> rateData = new ArrayList<>();
//        rateData.add(latestRateData);
//        rateData.add(histRateData);
//
//        CompareCurrencyResponseDto compareCurrencyResponseDto = new CompareCurrencyResponseDto();
//        compareCurrencyResponseDto.setBaseCurrency(latestResponseDto.getBase());
//        compareCurrencyResponseDto.setComparedCurrency(comparedCurrency);
//        compareCurrencyResponseDto.setRateData(rateData);
//        compareCurrencyResponseDto.setCompareResult(compareResult);
//
//        return compareCurrencyResponseDto;
//    }


    @Mapping(target = "gifTitle", expression = "java(getGifResponseDto.getData().get(0).getTitle())")
    @Mapping(target = "images", expression = "java(getGifResponseDto.getData().get(0).getImages())")
    MyComparatorResponseDto fromCompareCurrencyAndGetGifResponseDto(CompareCurrencyResponseDto compareCurrencyResponseDto
            , GetGifResponseDto getGifResponseDto);
}

package com.example.mycurrencycomparator.mapper;

import com.example.mycurrencycomparator.dto.comparator.MyComparatorResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.RateDataDto;
import com.example.mycurrencycomparator.dto.gif.GetGifResponseDto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-14T16:19:18+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public CompareCurrencyResponseDto fromComparedCurrencyAndDatesAndExchApiResponseDtos(String comparedCurrency, LocalDate localDateToday, LocalDate localDateYesterday, ExchApiResponseDto histResponseDto, ExchApiResponseDto latestResponseDto) {
        if ( comparedCurrency == null && localDateToday == null && localDateYesterday == null && histResponseDto == null && latestResponseDto == null ) {
            return null;
        }

        CompareCurrencyResponseDto compareCurrencyResponseDto = new CompareCurrencyResponseDto();

        if ( comparedCurrency != null ) {
            compareCurrencyResponseDto.setComparedCurrency( comparedCurrency );
        }
        if ( latestResponseDto != null ) {
            compareCurrencyResponseDto.setBaseCurrency( latestResponseDto.getBase() );
        }
        compareCurrencyResponseDto.setCompareResult( getCompareResult(comparedCurrency, histResponseDto, latestResponseDto) );
        compareCurrencyResponseDto.setRateData( getRateData(comparedCurrency, localDateToday, localDateYesterday, histResponseDto, latestResponseDto) );

        return compareCurrencyResponseDto;
    }

    @Override
    public MyComparatorResponseDto fromCompareCurrencyAndGetGifResponseDto(CompareCurrencyResponseDto compareCurrencyResponseDto, GetGifResponseDto getGifResponseDto) {
        if ( compareCurrencyResponseDto == null && getGifResponseDto == null ) {
            return null;
        }

        MyComparatorResponseDto myComparatorResponseDto = new MyComparatorResponseDto();

        if ( compareCurrencyResponseDto != null ) {
            myComparatorResponseDto.setComparedCurrency( compareCurrencyResponseDto.getComparedCurrency() );
            myComparatorResponseDto.setBaseCurrency( compareCurrencyResponseDto.getBaseCurrency() );
            myComparatorResponseDto.setCompareResult( compareCurrencyResponseDto.getCompareResult() );
            List<RateDataDto> list = compareCurrencyResponseDto.getRateData();
            if ( list != null ) {
                myComparatorResponseDto.setRateData( new ArrayList<RateDataDto>( list ) );
            }
        }
        myComparatorResponseDto.setGifTitle( getGifResponseDto.getData().get(0).getTitle() );
        myComparatorResponseDto.setImages( getGifResponseDto.getData().get(0).getImages() );

        return myComparatorResponseDto;
    }

    @Override
    public RateDataDto fromDateAndRate(String date, Double rate) {
        if ( date == null && rate == null ) {
            return null;
        }

        RateDataDto rateDataDto = new RateDataDto();

        if ( date != null ) {
            rateDataDto.setDate( date );
        }
        if ( rate != null ) {
            rateDataDto.setRate( rate );
        }

        return rateDataDto;
    }
}

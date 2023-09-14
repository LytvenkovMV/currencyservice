package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.client.CurrencyClient;
import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.RateDataDto;
import com.example.mycurrencycomparator.mapper.MapStructMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CurrencyServiceFeignImplTest {

    @Mock
    private CurrencyClient currencyClient;

    @Mock
    private MapStructMapper mapStructMapper;

    @InjectMocks
    private CurrencyServiceFeignImpl currencyService;

    @Test
    void getCompareResult() {


        //given

        Map<String, Double> histRates = new LinkedHashMap<>();
        histRates.put("RUB", 35.01);

        Map<String, Double> latestRates = new LinkedHashMap<>();
        latestRates.put("RUB", 35.02);

        ExchApiResponseDto histResp = new ExchApiResponseDto();
        histResp.setDisclaimer("disclaimer");
        histResp.setLicense("license");
        histResp.setTimestamp(Long.valueOf(1234567890));
        histResp.setBase("USD");
        histResp.setRates(histRates);

        ExchApiResponseDto latestResp = new ExchApiResponseDto();
        latestResp.setDisclaimer("disclaimer");
        latestResp.setLicense("license");
        latestResp.setTimestamp(Long.valueOf(1234567899));
        latestResp.setBase("USD");
        latestResp.setRates(latestRates);


        LocalDate dateToday = java.time.LocalDate.now();
        LocalDate dateYesterday = dateToday.minusDays(1);


        List<RateDataDto> rateData = new ArrayList<>();
        rateData.add(new RateDataDto(dateToday.toString(), 35.02));
        rateData.add(new RateDataDto(dateYesterday.toString(), 35.01));

        CompareCurrencyResponseDto compareCurrencyResponseDto = new CompareCurrencyResponseDto();
        compareCurrencyResponseDto.setBaseCurrency("USD");
        compareCurrencyResponseDto.setComparedCurrency("RUB");
        compareCurrencyResponseDto.setCompareResult("broke");
        compareCurrencyResponseDto.setRateData(rateData);


        Mockito
                .when(currencyClient.getHistoricalRate(any(), any(), any(), any()))
                .thenReturn(new ResponseEntity(histResp, HttpStatus.OK));

        Mockito
                .when(currencyClient.getLatestRate(any(), any(), any()))
                .thenReturn(new ResponseEntity(latestResp, HttpStatus.OK));

        Mockito
                .when(mapStructMapper.fromComparedCurrencyAndDatesAndExchApiResponseDtos(any(), any(), any(), any(), any()))
                .thenReturn(compareCurrencyResponseDto);

        // then

        ResponseEntity<CompareCurrencyResponseDto> respEntity = currencyService.getCompareResult("RUB");


        //when

        assertEquals("USD", respEntity.getBody().getBaseCurrency());
        assertEquals("RUB", respEntity.getBody().getComparedCurrency());
        assertEquals("broke", respEntity.getBody().getCompareResult());
        assertEquals(dateToday.toString(), respEntity.getBody().getRateData().get(0).getDate());
        assertEquals(35.02, respEntity.getBody().getRateData().get(0).getRate());
        assertEquals(dateYesterday.toString(), respEntity.getBody().getRateData().get(1).getDate());
        assertEquals(35.01, respEntity.getBody().getRateData().get(1).getRate());
    }
}
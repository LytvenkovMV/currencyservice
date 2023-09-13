package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.client.CurrencyClient;
import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("classpath:/application.properties")
@ExtendWith(MockitoExtension.class)
class CurrencyServiceFeignImplTest {

    @Value("${service.currency.apiKey}")
    String apiKey;

    @Value("${service.currency.baseCurrency}")
    String baseCurrency;

    @Mock
    private CurrencyClient currencyClient;

    @InjectMocks
    private CurrencyServiceFeignImpl currencyService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(currencyService, "apiKey", apiKey);
        ReflectionTestUtils.setField(currencyService, "baseCurrency", baseCurrency);
    }

    @Test
    void getCompareResult() {


        //given

        Map<String, Double> histRates = new LinkedHashMap<>();
        histRates.put("RUB", 95.01);

        Map<String, Double> latestRates = new LinkedHashMap<>();
        latestRates.put("RUB", 95.02);

        ExchApiResponseDto histResp = new ExchApiResponseDto();
        histResp.setDisclaimer("disclaimer");
        histResp.setLicense("license");
        histResp.setTimestamp(Long.valueOf(1234567890));
        histResp.setBase(baseCurrency);
        histResp.setRates(histRates);

        ExchApiResponseDto latestResp = new ExchApiResponseDto();
        latestResp.setDisclaimer("disclaimer");
        latestResp.setLicense("license");
        latestResp.setTimestamp(Long.valueOf(1234567899));
        latestResp.setBase(baseCurrency);
        latestResp.setRates(latestRates);


        LocalDate dateToday = java.time.LocalDate.now();
        LocalDate dateYesterday = dateToday.minusDays(1);


        Mockito
                .when(currencyClient.getHistoricalRate(dateYesterday.toString(), apiKey, baseCurrency, "RUB"))
                .thenReturn(new ResponseEntity(histResp, HttpStatus.OK));

        Mockito
                .when(currencyClient.getLatestRate(apiKey, baseCurrency, "RUB"))
                .thenReturn(new ResponseEntity(latestResp, HttpStatus.OK));


        // then

        ResponseEntity<CompareCurrencyResponseDto> respEntity = currencyService.getCompareResult("RUB");


        //when

        assertEquals(baseCurrency, respEntity.getBody().getBaseCurrency());
        assertEquals("RUB", respEntity.getBody().getComparedCurrency());
        assertEquals("broke", respEntity.getBody().getCompareResult());
        assertEquals(dateToday.toString(), respEntity.getBody().getRateData().get(0).getDate());
        assertEquals(95.02, respEntity.getBody().getRateData().get(0).getRate());
        assertEquals(dateYesterday.toString(), respEntity.getBody().getRateData().get(1).getDate());
        assertEquals(95.01, respEntity.getBody().getRateData().get(1).getRate());
    }
}
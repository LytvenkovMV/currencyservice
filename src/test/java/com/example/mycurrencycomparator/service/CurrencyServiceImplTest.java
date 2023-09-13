package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
////////////////////////////@TestPropertySource("classpath:/application.properties")
@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

//    @Value("${service.currency.url}")
//    String url;
//
//    @Value("${service.currency.apiKey}")
//    String apiKey;
//
//    @Value("${service.currency.baseCurrency}")
//    String baseCurrency;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyServiceImpl currencyService;
//
//    @BeforeEach
//    public void setUp() {
//        ReflectionTestUtils.setField(currencyService, "url", url);
//        ReflectionTestUtils.setField(currencyService, "apiKey", apiKey);
//        ReflectionTestUtils.setField(currencyService, "baseCurrency", baseCurrency);
//    }

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

//        String p1 = "app_id=" + apiKey;
//        String p2 = "base=" + baseCurrency;
//        String p3 = "symbols=" + "RUB";
//        String histRequest = url + "/historical/" + dateYesterday.toString() + ".json?" + p1 + "&" + p2 + "&" + p3;
//        String latestRequest = url + "/latest.json?" + p1 + "&" + p2 + "&" + p3;


        Mockito
                .when(restTemplate.getForEntity(anyString(), any()))
                .thenReturn(new ResponseEntity(histResp, HttpStatus.OK));

        Mockito
                .when(restTemplate.getForEntity(anyString(), any()))
                .thenReturn(new ResponseEntity(latestResp, HttpStatus.OK));


        // then

        ResponseEntity<CompareCurrencyResponseDto> respEntity = currencyService.getCompareResult("RUB");


        //when

        assertEquals("USD", respEntity.getBody().getBaseCurrency());
        assertEquals("RUB", respEntity.getBody().getComparedCurrency());
        assertEquals("broke", respEntity.getBody().getCompareResult());
        assertEquals(dateToday.toString(), respEntity.getBody().getRateData().get(0).getDate());
        assertEquals(95.02, respEntity.getBody().getRateData().get(0).getRate());
        assertEquals(dateYesterday.toString(), respEntity.getBody().getRateData().get(1).getDate());
        assertEquals(95.01, respEntity.getBody().getRateData().get(1).getRate());
    }
}

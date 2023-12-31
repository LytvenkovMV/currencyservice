package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import com.example.mycurrencycomparator.exception.CurrencyServiceException;
import com.example.mycurrencycomparator.mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@Profile("profile2")
@PropertySource("classpath:/application.properties")
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${service.currency.url}")
    private String url;

    @Value("${service.currency.apiKey}")
    private String apiKey;

    @Value("${service.currency.baseCurrency}")
    private String baseCurrency;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MapStructMapper mapStructMapper;

    @Override
    public ResponseEntity<CompareCurrencyResponseDto> getCompareResult(String comparedCurrency) {

        LocalDate localDateToday = java.time.LocalDate.now();
        LocalDate localDateYesterday = localDateToday.minusDays(1);

        String date = localDateYesterday.toString();
        String p1 = "app_id=" + apiKey;
        String p2 = "base=" + baseCurrency;
        String p3 = "symbols=" + comparedCurrency;
        String histRequest = url + "/historical/" + date + ".json?" + p1 + "&" + p2 + "&" + p3;
        String latestRequest = url + "/latest.json?" + p1 + "&" + p2 + "&" + p3;

        ResponseEntity<ExchApiResponseDto> histResponse;
        ResponseEntity<ExchApiResponseDto> latestResponse;
        try {
            histResponse = restTemplate.getForEntity(histRequest, ExchApiResponseDto.class);
            latestResponse = restTemplate.getForEntity(latestRequest, ExchApiResponseDto.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new CurrencyServiceException();
        }

        CompareCurrencyResponseDto responseDto = mapStructMapper.fromComparedCurrencyAndDatesAndExchApiResponseDtos(
                comparedCurrency
                , localDateToday
                , localDateYesterday
                , histResponse.getBody()
                , latestResponse.getBody());

        return ResponseEntity.ok(responseDto);
    }
}

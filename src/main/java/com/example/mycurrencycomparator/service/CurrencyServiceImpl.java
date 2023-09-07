package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import com.example.mycurrencycomparator.exception.CurrencyServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@Profile("profile2")
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${service.currency.url}")
    private String url;

    @Value("${service.currency.apiKey}")
    private String apiKey;

    @Value("${service.currency.baseCurrency}")
    private String baseCurrency;

    @Autowired
    private RestTemplate restTemplate;

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

        ResponseEntity<ExchApiResponseDto> histResponse = null;
        ResponseEntity<ExchApiResponseDto> latestResponse = null;
        try {
            histResponse = restTemplate.getForEntity(histRequest, ExchApiResponseDto.class);
            latestResponse = restTemplate.getForEntity(latestRequest, ExchApiResponseDto.class);
        } catch (RestClientException e) {
            throw new CurrencyServiceException();
        }

        CompareCurrencyResponseDto responseDto = DtoMapper.getCompareCurrencyResponseDto(comparedCurrency, localDateToday, localDateYesterday, histResponse.getBody(), latestResponse.getBody());

        return ResponseEntity.ok(responseDto);
    }
}

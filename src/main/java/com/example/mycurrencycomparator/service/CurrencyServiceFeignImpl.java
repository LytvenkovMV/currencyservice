package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.client.CurrencyClient;
import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import com.example.mycurrencycomparator.exception.CurrencyServiceException;
import com.example.mycurrencycomparator.mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Profile("profile1")
public class CurrencyServiceFeignImpl implements CurrencyService {

    @Value("${service.currency.apiKey}")
    private String apiKey;

    @Value("${service.currency.baseCurrency}")
    private String baseCurrency;

    @Autowired
    private CurrencyClient currencyClient;

    @Autowired
    private MapStructMapper mapStructMapper;

    @Override
    public ResponseEntity<CompareCurrencyResponseDto> getCompareResult(String comparedCurrency) {

        LocalDate localDateToday = LocalDate.now();
        LocalDate localDateYesterday = localDateToday.minusDays(1);

        ResponseEntity<ExchApiResponseDto> histResponse;
        ResponseEntity<ExchApiResponseDto> latestResponse;
        try {
            histResponse = currencyClient.getHistoricalRate(localDateYesterday.toString(), apiKey, baseCurrency, comparedCurrency);
            latestResponse = currencyClient.getLatestRate(apiKey, baseCurrency, comparedCurrency);
        } catch (Exception e) {
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

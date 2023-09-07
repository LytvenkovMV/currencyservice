package com.example.mycurrencycomparator.client;

import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "currencyclient", url = "${service.currency.url}")
public interface CurrencyClient {

    @GetMapping(value = "/historical/{date}.json")
    ResponseEntity<ExchApiResponseDto> getHistoricalRate(@PathVariable String date
            , @RequestParam String app_id
            , @RequestParam String base
            , @RequestParam String symbols);

    @GetMapping(value = "/latest.json")
    ResponseEntity<ExchApiResponseDto> getLatestRate(@RequestParam String app_id
            , @RequestParam String base
            , @RequestParam String symbols);
}

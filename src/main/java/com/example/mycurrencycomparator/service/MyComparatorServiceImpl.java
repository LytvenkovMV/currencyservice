package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.comparator.MyComparatorResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.gif.GetGifResponseDto;
import com.example.mycurrencycomparator.enumerator.CurrencyCodes;
import com.example.mycurrencycomparator.exception.WrongCodeException;
import com.example.mycurrencycomparator.mapper.MapStructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyComparatorServiceImpl implements MyComparatorService {

    @Value("${service.currency.defaultCurrency}")
    private String defaultCurrency;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private GifsService gifsService;

    @Autowired
    MapStructMapper mapStructMapper;

    @Override
    public ResponseEntity<MyComparatorResponseDto> compare(Optional<String> code) {

        String comparedCurrency = defaultCurrency;
        if (code.isPresent()) {
            if (checkCurrencyCode(code.get())) comparedCurrency = code.get();
            else throw new WrongCodeException();
        }

        ResponseEntity<CompareCurrencyResponseDto> compareCurrencyResponseEntity = currencyService.getCompareResult(comparedCurrency);

        String qWord = compareCurrencyResponseEntity.getBody().getCompareResult();
        ResponseEntity<GetGifResponseDto> getGifResponseEntity = gifsService.getGif(qWord);

        MyComparatorResponseDto responseDto = mapStructMapper.fromCompareCurrencyAndGetGifResponseDto(compareCurrencyResponseEntity.getBody()
                , getGifResponseEntity.getBody());

        return ResponseEntity.ok(responseDto);
    }


    private boolean checkCurrencyCode(String inputCode) {
        for (CurrencyCodes code : CurrencyCodes.values()) {
            if (inputCode.contentEquals(code.getStringCode())) return true;
        }
        return false;
    }
}

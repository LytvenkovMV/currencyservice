package com.example.mycurrencycomparator.mapper;

import com.example.mycurrencycomparator.dto.comparator.MyComparatorResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.ExchApiResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.RateDataDto;
import com.example.mycurrencycomparator.dto.gif.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MapStructMapperTest {

    @Autowired
    MapStructMapper mapStructMapper;

    @Test
    void fromComparedCurrencyAndDatesAndExchApiResponseDtos() {


        //given

        String comparedCurrency = "ALL";
        LocalDate localDateToday = java.time.LocalDate.of(2020, 01, 22);
        LocalDate localDateYesterday = java.time.LocalDate.of(2020, 01, 21);

        Map<String, Double> histRates = new LinkedHashMap<>();
        histRates.put("ALL", 95.02);

        Map<String, Double> latestRates = new LinkedHashMap<>();
        latestRates.put("ALL", 95.01);

        ExchApiResponseDto histResponseDto = new ExchApiResponseDto();
        histResponseDto.setDisclaimer("disclaimer");
        histResponseDto.setLicense("license");
        histResponseDto.setTimestamp(Long.valueOf(1234567890));
        histResponseDto.setBase("USD");
        histResponseDto.setRates(histRates);

        ExchApiResponseDto latestResponseDto = new ExchApiResponseDto();
        latestResponseDto.setDisclaimer("disclaimer");
        latestResponseDto.setLicense("license");
        latestResponseDto.setTimestamp(Long.valueOf(1234567899));
        latestResponseDto.setBase("USD");
        latestResponseDto.setRates(latestRates);


        //then

        CompareCurrencyResponseDto responseDto = mapStructMapper.fromComparedCurrencyAndDatesAndExchApiResponseDtos(
                comparedCurrency
                , localDateToday
                , localDateYesterday
                , histResponseDto
                , latestResponseDto);


        //when

        assertEquals("rich", responseDto.getCompareResult());
        assertEquals("ALL", responseDto.getComparedCurrency());
        assertEquals("USD", responseDto.getBaseCurrency());
        assertEquals("2020-01-22", responseDto.getRateData().get(0).getDate());
        assertEquals("2020-01-21", responseDto.getRateData().get(1).getDate());
        assertEquals(95.01, responseDto.getRateData().get(0).getRate());
        assertEquals(95.02, responseDto.getRateData().get(1).getRate());
    }

    @Test
    void fromCompareCurrencyAndGetGifResponseDto() {


        //given

        RateDataDto histRateDataDto = new RateDataDto("2020-02-11", 95.01);
        RateDataDto latestRateDataDto = new RateDataDto("2020-02-12", 95.02);

        List<RateDataDto> rateData = new ArrayList<>();
        rateData.add(latestRateDataDto);
        rateData.add(histRateDataDto);

        CompareCurrencyResponseDto compareCurrencyResponseDto = new CompareCurrencyResponseDto();
        compareCurrencyResponseDto.setBaseCurrency("USD");
        compareCurrencyResponseDto.setComparedCurrency("RUB");
        compareCurrencyResponseDto.setCompareResult("broke");
        compareCurrencyResponseDto.setRateData(rateData);


        ImageOriginalDto imageOriginalDto = new ImageOriginalDto();
        imageOriginalDto.setHeight("height_o");
        imageOriginalDto.setWidth("width_o");
        imageOriginalDto.setSize("size_o");
        imageOriginalDto.setUrl("url_o");
        imageOriginalDto.setMp4Size("mp4_size_o");
        imageOriginalDto.setMp4("mp4_o");
        imageOriginalDto.setWebpSize("webp_size_o");
        imageOriginalDto.setWebp("webp_o");
        imageOriginalDto.setFrames("frames");
        imageOriginalDto.setHash("hash");

        ImageFixedDto imageFixedHeightDto = new ImageFixedDto();
        imageFixedHeightDto.setHeight("height_fh");
        imageFixedHeightDto.setWidth("width_fh");
        imageFixedHeightDto.setSize("size_fh");
        imageFixedHeightDto.setUrl("url_fh");
        imageFixedHeightDto.setMp4Size("mp4_size_fh");
        imageFixedHeightDto.setMp4("mp4_fh");
        imageFixedHeightDto.setWebpSize("webp_size_fh");
        imageFixedHeightDto.setWebp("webp_fh");

        ImageFixedDownsampledDto imageFixedHeihgtDownsampledDto = new ImageFixedDownsampledDto();
        imageFixedHeihgtDownsampledDto.setHeight("height_fhd");
        imageFixedHeihgtDownsampledDto.setWidth("width_fhd");
        imageFixedHeihgtDownsampledDto.setSize("size_fhd");
        imageFixedHeihgtDownsampledDto.setUrl("url_fhd");
        imageFixedHeihgtDownsampledDto.setWebpSize("webp_size_fhd");
        imageFixedHeihgtDownsampledDto.setWebp("webp_fhd");

        ImageFixedDto imageFixedHeightSmallDto = new ImageFixedDto();
        imageFixedHeightSmallDto.setHeight("height_fhs");
        imageFixedHeightSmallDto.setWidth("width_fhs");
        imageFixedHeightSmallDto.setSize("size_fhs");
        imageFixedHeightSmallDto.setUrl("url_fhs");
        imageFixedHeightSmallDto.setMp4Size("mp4_size_fhs");
        imageFixedHeightSmallDto.setMp4("mp4_fhs");
        imageFixedHeightSmallDto.setWebpSize("webp_size_fhs");
        imageFixedHeightSmallDto.setWebp("webp_fhs");

        ImageFixedDto imageFixedWidthDto = new ImageFixedDto();
        imageFixedWidthDto.setHeight("height_fw");
        imageFixedWidthDto.setWidth("width_fw");
        imageFixedWidthDto.setSize("size_fw");
        imageFixedWidthDto.setUrl("url_fw");
        imageFixedWidthDto.setMp4Size("mp4_size_fw");
        imageFixedWidthDto.setMp4("mp4_fw");
        imageFixedWidthDto.setWebpSize("webp_size_fw");
        imageFixedWidthDto.setWebp("webp_fw");

        ImageFixedDownsampledDto imageFixedWidthDownsampledDto = new ImageFixedDownsampledDto();
        imageFixedWidthDownsampledDto.setHeight("height_fwd");
        imageFixedWidthDownsampledDto.setWidth("width_fwd");
        imageFixedWidthDownsampledDto.setSize("size_fwd");
        imageFixedWidthDownsampledDto.setUrl("url_fwd");
        imageFixedWidthDownsampledDto.setWebpSize("webp_size_fwd");
        imageFixedWidthDownsampledDto.setWebp("webp_fwd");

        ImageFixedDto imageFixedWidthSmallDto = new ImageFixedDto();
        imageFixedWidthSmallDto.setHeight("height_fws");
        imageFixedWidthSmallDto.setWidth("width_fws");
        imageFixedWidthSmallDto.setSize("size_fws");
        imageFixedWidthSmallDto.setUrl("url_fws");
        imageFixedWidthSmallDto.setMp4Size("mp4_size_fws");
        imageFixedWidthSmallDto.setMp4("mp4_fws");
        imageFixedWidthSmallDto.setWebpSize("webp_size_fws");
        imageFixedWidthSmallDto.setWebp("webp_fws");

        ImagesDto imagesDto = new ImagesDto();
        imagesDto.setOriginal(imageOriginalDto);
        imagesDto.setFixedHeight(imageFixedHeightDto);
        imagesDto.setFixedHeightDownsampled(imageFixedHeihgtDownsampledDto);
        imagesDto.setFixedHeightSmall(imageFixedHeightSmallDto);
        imagesDto.setFixedWidth(imageFixedWidthDto);
        imagesDto.setFixedWidthDownsampled(imageFixedWidthDownsampledDto);
        imagesDto.setFixedWidthSmall(imageFixedWidthSmallDto);

        GifDto gifDto = new GifDto();
        gifDto.setTitle("title1");
        gifDto.setImages(imagesDto);

        ArrayList<GifDto> data = new ArrayList<>();
        data.add(gifDto);

        GetGifResponseDto getGifResponseDto = new GetGifResponseDto();
        getGifResponseDto.setData(data);


        //then

        MyComparatorResponseDto responseDto = mapStructMapper.fromCompareCurrencyAndGetGifResponseDto(
                compareCurrencyResponseDto, getGifResponseDto);


        //when

        assertEquals("title1", responseDto.getGifTitle());
        assertEquals("RUB", responseDto.getComparedCurrency());
        assertEquals("USD", responseDto.getBaseCurrency());
        assertEquals("broke", responseDto.getCompareResult());
        assertEquals("2020-02-12", responseDto.getRateData().get(0).getDate());
        assertEquals("2020-02-11", responseDto.getRateData().get(1).getDate());
        assertEquals(95.02, responseDto.getRateData().get(0).getRate());
        assertEquals(95.01, responseDto.getRateData().get(1).getRate());
        assertEquals(imagesDto, responseDto.getImages());
    }
}
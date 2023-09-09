package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.comparator.MyComparatorResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.CompareCurrencyResponseDto;
import com.example.mycurrencycomparator.dto.currencyrate.RateDataDto;
import com.example.mycurrencycomparator.dto.gif.*;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource("classpath:/application.properties")
@ExtendWith(MockitoExtension.class)
class MyComparatorServiceImplTest {

    @Value("${service.currency.baseCurrency}")
    String baseCurrency;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private GifsService gifsService;

    @InjectMocks
    private MyComparatorServiceImpl myComparatorService;

    @Test
    void compare() {


        //given

        LocalDate dateToday = java.time.LocalDate.now();
        LocalDate dateYesterday = dateToday.minusDays(1);

        RateDataDto histRateDataDto = new RateDataDto(dateYesterday.toString(), 95.02);
        RateDataDto latestRateDataDto = new RateDataDto(dateToday.toString(), 95.01);

        List<RateDataDto> rateData = new ArrayList<>();
        rateData.add(latestRateDataDto);
        rateData.add(histRateDataDto);

        CompareCurrencyResponseDto compareCurrencyResponseDto = new CompareCurrencyResponseDto();
        compareCurrencyResponseDto.setBase_currency(baseCurrency);
        compareCurrencyResponseDto.setCompared_currency("RUB");
        compareCurrencyResponseDto.setCompare_result("rich");
        compareCurrencyResponseDto.setRate_data(rateData);

        Mockito
                .when(currencyService.getCompareResult("RUB"))
                .thenReturn(new ResponseEntity(compareCurrencyResponseDto, HttpStatus.OK));


        AnalyticsEventDto analyticsOnloadDto = new AnalyticsEventDto();
        AnalyticsEventDto analyticsOnclickDto = new AnalyticsEventDto();
        AnalyticsEventDto analyticsOnsentDto = new AnalyticsEventDto();

        analyticsOnloadDto.setUrl("onload_url");
        analyticsOnclickDto.setUrl("onclick_url");
        analyticsOnsentDto.setUrl("onsent_url");

        AnalyticsDto analyticsDto = new AnalyticsDto();
        analyticsDto.setOnload(analyticsOnloadDto);
        analyticsDto.setOnclick(analyticsOnclickDto);
        analyticsDto.setOnsent(analyticsOnsentDto);

        UserDto userDto = new UserDto();
        userDto.setAvatar_url("avatar_url");
        userDto.setBanner_image("banner_image");
        userDto.setBanner_url("banner_url");
        userDto.setProfile_url("profile_url");
        userDto.setUsername("username");
        userDto.setDisplay_name("display_name");
        userDto.setDescription("description");
        userDto.setInstagram_url("instagram_url");
        userDto.setWebsite_url("website_url");
        userDto.setIs_verified("is_verified");

        ImageOriginalDto imageOriginalDto = new ImageOriginalDto();
        imageOriginalDto.setHeight("height_o");
        imageOriginalDto.setWidth("width_o");
        imageOriginalDto.setSize("size_o");
        imageOriginalDto.setUrl("url_o");
        imageOriginalDto.setMp4_size("mp4_size_o");
        imageOriginalDto.setMp4("mp4_o");
        imageOriginalDto.setWebp_size("webp_size_o");
        imageOriginalDto.setWebp("webp_o");
        imageOriginalDto.setFrames("frames");
        imageOriginalDto.setHash("hash");

        ImageFixedDto imageFixedHeightDto = new ImageFixedDto();
        imageFixedHeightDto.setHeight("height_fh");
        imageFixedHeightDto.setWidth("width_fh");
        imageFixedHeightDto.setSize("size_fh");
        imageFixedHeightDto.setUrl("url_fh");
        imageFixedHeightDto.setMp4_size("mp4_size_fh");
        imageFixedHeightDto.setMp4("mp4_fh");
        imageFixedHeightDto.setWebp_size("webp_size_fh");
        imageFixedHeightDto.setWebp("webp_fh");

        ImageFixedDownsampledDto imageFixedHeihgtDownsampledDto = new ImageFixedDownsampledDto();
        imageFixedHeihgtDownsampledDto.setHeight("height_fhd");
        imageFixedHeihgtDownsampledDto.setWidth("width_fhd");
        imageFixedHeihgtDownsampledDto.setSize("size_fhd");
        imageFixedHeihgtDownsampledDto.setUrl("url_fhd");
        imageFixedHeihgtDownsampledDto.setWebp_size("webp_size_fhd");
        imageFixedHeihgtDownsampledDto.setWebp("webp_fhd");

        ImageFixedDto imageFixedHeightSmallDto = new ImageFixedDto();
        imageFixedHeightSmallDto.setHeight("height_fhs");
        imageFixedHeightSmallDto.setWidth("width_fhs");
        imageFixedHeightSmallDto.setSize("size_fhs");
        imageFixedHeightSmallDto.setUrl("url_fhs");
        imageFixedHeightSmallDto.setMp4_size("mp4_size_fhs");
        imageFixedHeightSmallDto.setMp4("mp4_fhs");
        imageFixedHeightSmallDto.setWebp_size("webp_size_fhs");
        imageFixedHeightSmallDto.setWebp("webp_fhs");

        ImageFixedDto imageFixedWidthDto = new ImageFixedDto();
        imageFixedWidthDto.setHeight("height_fw");
        imageFixedWidthDto.setWidth("width_fw");
        imageFixedWidthDto.setSize("size_fw");
        imageFixedWidthDto.setUrl("url_fw");
        imageFixedWidthDto.setMp4_size("mp4_size_fw");
        imageFixedWidthDto.setMp4("mp4_fw");
        imageFixedWidthDto.setWebp_size("webp_size_fw");
        imageFixedWidthDto.setWebp("webp_fw");

        ImageFixedDownsampledDto imageFixedWidthDownsampledDto = new ImageFixedDownsampledDto();
        imageFixedWidthDownsampledDto.setHeight("height_fwd");
        imageFixedWidthDownsampledDto.setWidth("width_fwd");
        imageFixedWidthDownsampledDto.setSize("size_fwd");
        imageFixedWidthDownsampledDto.setUrl("url_fwd");
        imageFixedWidthDownsampledDto.setWebp_size("webp_size_fwd");
        imageFixedWidthDownsampledDto.setWebp("webp_fwd");

        ImageFixedDto imageFixedWidthSmallDto = new ImageFixedDto();
        imageFixedWidthSmallDto.setHeight("height_fws");
        imageFixedWidthSmallDto.setWidth("width_fws");
        imageFixedWidthSmallDto.setSize("size_fws");
        imageFixedWidthSmallDto.setUrl("url_fws");
        imageFixedWidthSmallDto.setMp4_size("mp4_size_fws");
        imageFixedWidthSmallDto.setMp4("mp4_fws");
        imageFixedWidthSmallDto.setWebp_size("webp_size_fws");
        imageFixedWidthSmallDto.setWebp("webp_fws");

        ImagesDto imagesDto = new ImagesDto();
        imagesDto.setOriginal(imageOriginalDto);
        imagesDto.setFixed_height(imageFixedHeightDto);
        imagesDto.setFixed_height_downsampled(imageFixedHeihgtDownsampledDto);
        imagesDto.setFixed_height_small(imageFixedHeightSmallDto);
        imagesDto.setFixed_width(imageFixedWidthDto);
        imagesDto.setFixed_width_downsampled(imageFixedWidthDownsampledDto);
        imagesDto.setFixed_width_small(imageFixedWidthSmallDto);

        GifDto gifDto = new GifDto();
        gifDto.setType("type");
        gifDto.setId("id");
        gifDto.setUrl("url");
        gifDto.setBitly_gif_url("bitly_gif_url");
        gifDto.setBitly_url("bitly_url");
        gifDto.setEmbed_url("embed_url");
        gifDto.setUsername("username");
        gifDto.setSource("source");
        gifDto.setTitle("title");
        gifDto.setRating("rating");
        gifDto.setContent_url("content_url");
        gifDto.setSource_tld("source_tld");
        gifDto.setSource_post_url("source_post_url");
        gifDto.setIs_sticker(0);
        gifDto.setImport_datetime("import_datetime");
        gifDto.setTrending_datetime("trending_datetime");
        gifDto.setImages(imagesDto);
        gifDto.setUser(userDto);
        gifDto.setAnalytics_response_payload("analytics_payload");
        gifDto.setAnalytics(analyticsDto);

        ArrayList<GifDto> data = new ArrayList<>();
        data.add(gifDto);

        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setTotal_count(1);
        paginationDto.setCount(1);
        paginationDto.setOffset(33);

        MetaDto metaDto = new MetaDto();
        metaDto.setMsg("meta_message");
        metaDto.setResponse_id("meta_id");
        metaDto.setStatus(200);

        GetGifResponseDto getGifResponseDto = new GetGifResponseDto();
        getGifResponseDto.setData(data);
        getGifResponseDto.setPagination(paginationDto);
        getGifResponseDto.setMeta(metaDto);

        Mockito
                .when(gifsService.getGif("rich"))
                .thenReturn(new ResponseEntity(getGifResponseDto, HttpStatus.OK));


        //then

        ResponseEntity<MyComparatorResponseDto> respEntity = myComparatorService.compare(Optional.of("RUB"));


        //when

        assertEquals(baseCurrency, respEntity.getBody().getBase_currency());
        assertEquals("RUB", respEntity.getBody().getCompared_currency());
        assertEquals("rich", respEntity.getBody().getCompare_result());
        assertEquals(dateToday.toString(), respEntity.getBody().getRate_data().get(0).getDate());
        assertEquals(95.01, respEntity.getBody().getRate_data().get(0).getRate());
        assertEquals(dateYesterday.toString(), respEntity.getBody().getRate_data().get(1).getDate());
        assertEquals(95.02, respEntity.getBody().getRate_data().get(1).getRate());
        assertEquals("title", respEntity.getBody().getGif_title());
        assertEquals(imagesDto, respEntity.getBody().getImages());
    }
}
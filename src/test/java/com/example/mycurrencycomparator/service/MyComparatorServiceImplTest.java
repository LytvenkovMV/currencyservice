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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MyComparatorServiceImplTest {

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
        compareCurrencyResponseDto.setBaseCurrency("USD");
        compareCurrencyResponseDto.setComparedCurrency("RUB");
        compareCurrencyResponseDto.setCompareResult("rich");
        compareCurrencyResponseDto.setRateData(rateData);

        Mockito
                .when(currencyService.getCompareResult(any()))
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
        userDto.setAvatarUrl("avatar_url");
        userDto.setBannerImage("banner_image");
        userDto.setBannerUrl("banner_url");
        userDto.setProfileUrl("profile_url");
        userDto.setUsername("username");
        userDto.setDisplayName("display_name");
        userDto.setDescription("description");
        userDto.setInstagramUrl("instagram_url");
        userDto.setWebsiteUrl("website_url");
        userDto.setIsVerified("is_verified");

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
        gifDto.setType("type");
        gifDto.setId("id");
        gifDto.setUrl("url");
        gifDto.setBitlyGifUrl("bitly_gif_url");
        gifDto.setBitlyUrl("bitly_url");
        gifDto.setEmbedUrl("embed_url");
        gifDto.setUsername("username");
        gifDto.setSource("source");
        gifDto.setTitle("title");
        gifDto.setRating("rating");
        gifDto.setContentUrl("content_url");
        gifDto.setSourceTld("source_tld");
        gifDto.setSourcePostUrl("source_post_url");
        gifDto.setIsSticker(0);
        gifDto.setImportDateTime("import_datetime");
        gifDto.setTrendingDateTime("trending_datetime");
        gifDto.setImages(imagesDto);
        gifDto.setUser(userDto);
        gifDto.setAnalyticsResponsePayload("analytics_payload");
        gifDto.setAnalytics(analyticsDto);

        ArrayList<GifDto> data = new ArrayList<>();
        data.add(gifDto);

        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setTotalCount(1);
        paginationDto.setCount(1);
        paginationDto.setOffset(1);

        MetaDto metaDto = new MetaDto();
        metaDto.setMsg("meta_message");
        metaDto.setResponseId("meta_id");
        metaDto.setStatus(200);

        GetGifResponseDto getGifResponseDto = new GetGifResponseDto();
        getGifResponseDto.setData(data);
        getGifResponseDto.setPagination(paginationDto);
        getGifResponseDto.setMeta(metaDto);

        Mockito
                .when(gifsService.getGif(any()))
                .thenReturn(new ResponseEntity(getGifResponseDto, HttpStatus.OK));


        //then

        ResponseEntity<MyComparatorResponseDto> respEntity = myComparatorService.compare(Optional.of("RUB"));


        //when

        assertEquals("USD", respEntity.getBody().getBaseCurrency());
        assertEquals("RUB", respEntity.getBody().getComparedCurrency());
        assertEquals("rich", respEntity.getBody().getCompareResult());
        assertEquals(dateToday.toString(), respEntity.getBody().getRateData().get(0).getDate());
        assertEquals(95.01, respEntity.getBody().getRateData().get(0).getRate());
        assertEquals(dateYesterday.toString(), respEntity.getBody().getRateData().get(1).getDate());
        assertEquals(95.02, respEntity.getBody().getRateData().get(1).getRate());
        assertEquals("title", respEntity.getBody().getGifTitle());
        assertEquals(imagesDto, respEntity.getBody().getImages());
    }
}
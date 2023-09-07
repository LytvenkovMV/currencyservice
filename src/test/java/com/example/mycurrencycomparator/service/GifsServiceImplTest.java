package com.example.mycurrencycomparator.service;

import com.example.mycurrencycomparator.dto.gif.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles(profiles = "profile1")
@TestPropertySource("classpath:/application.properties")
@ExtendWith(MockitoExtension.class)
class GifsServiceImplTest {

    @Value("${service.gifs.url}")
    private String url;

    @Value("${service.gifs.apiKey}")
    private String apiKey;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GifsServiceImpl gifsService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(gifsService, "url", url);
        ReflectionTestUtils.setField(gifsService, "apiKey", apiKey);
    }

    @Test
    @Disabled
    void getGif() {


        //given

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


        String p1 = "api_key=" + apiKey;
        String p2 = "q=" + "anything";
        String p3 = "limit=" + 1;
        String p4 = "offset=" + 33;
        String p5 = "bundle=" + "messaging_non_clips";
        String request = url + "?" + p1 + "&" + p2 + "&" + p3 + "&" + p4 + "&" + p5;


        Mockito
                .when(restTemplate.getForEntity(request, GetGifResponseDto.class))
                .thenReturn(new ResponseEntity(getGifResponseDto, HttpStatus.OK));


        // then

        ResponseEntity<GetGifResponseDto> respEntity = gifsService.getGif("anything");


        //when

        assertEquals("title", respEntity.getBody().getData().get(0).getTitle());
        assertEquals("username", respEntity.getBody().getData().get(0).getUsername());
        assertEquals(1, respEntity.getBody().getPagination().getCount());
        assertEquals(imagesDto, respEntity.getBody().getData().get(0).getImages());
    }
}
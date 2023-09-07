package com.example.mycurrencycomparator.dto.gif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GifDto {

    private String type;
    private String id;
    private String url;
    private String bitly_gif_url;
    private String bitly_url;
    private String embed_url;
    private String username;
    private String source;
    private String title;
    private String rating;
    private String content_url;
    private String source_tld;
    private String source_post_url;
    private int is_sticker;
    private String import_datetime;
    private String trending_datetime;
    private ImagesDto images;
    private UserDto user;
    private String analytics_response_payload;
    private AnalyticsDto analytics;
}

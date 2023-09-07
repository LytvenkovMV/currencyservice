package com.example.mycurrencycomparator.dto.gif;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageFixedDto {

    @ApiModelProperty(value = "Высота", required = true)
    private String height;

    @ApiModelProperty(value = "Ширина", required = true)
    private String width;

    @ApiModelProperty(value = "Размер", required = true)
    private String size;

    @ApiModelProperty(value = "Ссылка", required = true)
    private String url;

    @ApiModelProperty(value = "Размер mp4", required = true)
    private String mp4_size;

    @ApiModelProperty(value = "Ссылка mp4", required = true)
    private String mp4;

    @ApiModelProperty(value = "Размер webp", required = true)
    private String webp_size;

    @ApiModelProperty(value = "Ссылка webp", required = true)
    private String webp;
}

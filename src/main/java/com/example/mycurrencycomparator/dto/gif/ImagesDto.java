package com.example.mycurrencycomparator.dto.gif;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImagesDto {

    @ApiModelProperty(value = "Гифка оригинального размера", required = true)
    private ImageOriginalDto original;

    @ApiModelProperty(value = "Гифка фиксированной высоты", required = true)
    private ImageFixedDto fixed_height;

    @ApiModelProperty(value = "Гифка фиксированной высоты с пониженной дискретизацией", required = true)
    private ImageFixedDownsampledDto fixed_height_downsampled;

    @ApiModelProperty(value = "Гифка фиксированной высоты малого размера", required = true)
    private ImageFixedDto fixed_height_small;

    @ApiModelProperty(value = "Гифка фиксированной ширины", required = true)
    private ImageFixedDto fixed_width;

    @ApiModelProperty(value = "Гифка фиксированной ширины с пониженной дискретизацией", required = true)
    private ImageFixedDownsampledDto fixed_width_downsampled;

    @ApiModelProperty(value = "Гифка фиксированной ширины малого размера", required = true)
    private ImageFixedDto fixed_width_small;
}

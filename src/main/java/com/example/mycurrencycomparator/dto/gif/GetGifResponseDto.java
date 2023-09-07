package com.example.mycurrencycomparator.dto.gif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGifResponseDto {

    private ArrayList<GifDto> data;
    private PaginationDto pagination;
    private MetaDto meta;
}

package com.example.mycurrencycomparator.dto.gif;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDto {

    private int total_count;
    private int count;
    private int offset;
}

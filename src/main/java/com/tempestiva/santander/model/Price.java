package com.tempestiva.santander.model;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @CsvBindByPosition(position = 0)
    private Long id;

    @CsvBindByPosition(position = 1)
    private String instrumentName;

    @CsvBindByPosition(position = 2)
    private Double bid;

    @CsvBindByPosition(position = 3)
    private Double ask;

    @CsvBindByPosition(position = 4)
    @CsvDate("dd-MM-yyyy HH:mm:ss:SSS")
    private LocalDateTime timestamp;
}

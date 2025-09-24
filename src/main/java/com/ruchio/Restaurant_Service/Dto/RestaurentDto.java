package com.ruchio.Restaurant_Service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurentDto {
    private Long id;
    private String name;
    private String address;
}

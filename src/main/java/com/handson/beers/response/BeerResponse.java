package com.handson.beers.response;

import com.handson.beers.domain.Beer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerResponse {

    private Integer id;
    private String name;
    private Double price;


    public static BeerResponse valueOf (Beer beer) {
       return BeerResponse.builder()
                .id(beer.getId())
                .name(beer.getName())
                .price(beer.getPrice())
                .build();
    }
}

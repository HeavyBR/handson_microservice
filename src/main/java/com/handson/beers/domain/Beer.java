package com.handson.beers.domain;

import com.handson.beers.request.BeerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double price;


    public static Beer valueOf (BeerRequest request) {
        return Beer.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();
    }
}

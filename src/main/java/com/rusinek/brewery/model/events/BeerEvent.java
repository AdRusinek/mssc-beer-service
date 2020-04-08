package com.rusinek.brewery.model.events;

import com.rusinek.brewery.model.BeerDto;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Adrian Rusinek on 06.04.2020
 **/
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 721723840831914799L;

    private BeerDto beerDto;
}

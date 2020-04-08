package com.rusinek.brewery.model.events;

import com.rusinek.brewery.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * Created by Adrian Rusinek on 06.04.2020
 **/
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}

package com.rusinek.msscbeerservice.events;

import com.rusinek.msscbeerservice.web.model.BeerDto;

/**
 * Created by Adrian Rusinek on 06.04.2020
 **/
public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}

package com.rusinek.msscbeerservice.services.brewing;

import com.rusinek.msscbeerservice.domain.Beer;
import com.rusinek.brewery.model.events.BrewBeerEvent;
import com.rusinek.brewery.model.events.NewInventoryEvent;
import com.rusinek.msscbeerservice.repositories.BeerRepository;
import com.rusinek.brewery.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.rusinek.msscbeerservice.config.JmsConfig.BREWING_REQUEST_QUEUE;
import static com.rusinek.msscbeerservice.config.JmsConfig.NEW_INVENTORY_QUEUE;

/**
 * Created by Adrian Rusinek on 06.04.2020
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    @JmsListener(destination = BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {
        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH: " + beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(NEW_INVENTORY_QUEUE, newInventoryEvent);
    }

}

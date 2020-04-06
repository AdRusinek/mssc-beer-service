package com.rusinek.msscbeerservice.services;

import com.rusinek.msscbeerservice.domain.Beer;
import com.rusinek.msscbeerservice.events.BrewBeerEvent;
import com.rusinek.msscbeerservice.repositories.BeerRepository;
import com.rusinek.msscbeerservice.services.inventory.BeerInventoryService;
import com.rusinek.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rusinek.msscbeerservice.config.JmsConfig.BREWING_REQUEST_QUEUE;

/**
 * Created by Adrian Rusinek on 06.04.2020
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());
            log.debug("Min on hand is: " + beer.getMinOnHand());
            log.debug("Inventory is: " + invQOH);

            if (beer.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(BREWING_REQUEST_QUEUE,
                        new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}

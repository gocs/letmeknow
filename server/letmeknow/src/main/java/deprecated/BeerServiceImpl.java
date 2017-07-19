/*
package service.impl;

import dao.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import deprecated.Beer;
import org.springframework.stereotype.Service;
import service.BeerService;

@Service("beerService")
public class BeerServiceImpl implements BeerService {
    @Autowired
    private BeerRepository beerRepository;

    public void setBeerRepository(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Iterable<Beer> allBeers() {
        return beerRepository.findAll();
    }

}
*/
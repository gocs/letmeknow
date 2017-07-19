/*
package action;

import dao.BeerRepository;
import deprecated.Beer;
import service.BeerService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;


public class BeerAction extends BaseAction {
    private List<Beer> beers;
    @Autowired
    private BeerRepository beerRepository;

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeerRepository(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Action(value="/beer",results={@Result(type="json",name=SUCCESS)})
    public String Beer(){
        beers=beerRepository.findAll();
        return SUCCESS;
    }
}
*/

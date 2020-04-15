package com.handson.beers.service;


import com.handson.beers.domain.Beer;
import com.handson.beers.repository.BeerRepository;
import com.handson.beers.request.BeerRequest;
import com.handson.beers.response.BeerResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerService {

    private final BeerRepository beerRepository;

    //GET /beers
    //Return = BeerResponse
    public List<BeerResponse> getAllBeers() {
        return beerRepository.findAll().stream()
                .map(BeerResponse::valueOf)
                .collect(Collectors.toList());
    }


    public BeerResponse getBeerById(Integer id) throws ChangeSetPersister.NotFoundException {
        Optional<Beer> beer = Optional.ofNullable(beerRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));

        return BeerResponse.valueOf(beer.get());
    }

    public int createNewBeer (BeerRequest request) {

        Beer beerCreated = beerRepository.save(Beer.valueOf(request));

        return beerCreated.getId();
    }

    @Transactional
    public void deleteBeerById(Integer id) throws ChangeSetPersister.NotFoundException {
        beerRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        beerRepository.deleteById(id);
    }

    @Transactional
    public void updateBeer(Integer id, BeerRequest beerRequest) throws ChangeSetPersister.NotFoundException {
        Beer beer = beerRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        beer.setName(beerRequest.getName());
        beer.setPrice(beerRequest.getPrice());

        beerRepository.save(beer);
    }
}

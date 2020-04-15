package com.handson.beers.controller;

import com.handson.beers.request.BeerRequest;
import com.handson.beers.response.BeerResponse;
import com.handson.beers.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/beers")
@RequiredArgsConstructor
public class BeerController {

    private final BeerService service;

    @GetMapping
    public ResponseEntity<List<BeerResponse>> getAllBeers() {
        return ResponseEntity.ok().body(service.getAllBeers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeerResponse> getBeerbyId (@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok().body(service.getBeerById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createNewbeer(@RequestBody BeerRequest beerRequest, UriComponentsBuilder uriComponentsBuilder) {
        Integer beerCreated = service.createNewBeer(beerRequest);
        URI uri = uriComponentsBuilder.path("/beers/{id}").buildAndExpand(beerCreated).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeerById(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        service.deleteBeerById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBeer(@RequestBody BeerRequest beerRequest, @PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        service.updateBeer(id,beerRequest);
        return ResponseEntity.noContent().build();
    }
}

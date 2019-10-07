package org.scijava.howto.api.controller;

import org.scijava.howto.api.model.Howto;
import org.scijava.howto.api.repository.HowtoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/howtos"})
public class HowtoController {

  private HowtoRepository repository;

  HowtoController(HowtoRepository howtoRepository) {
      this.repository = howtoRepository;
  }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/search/{term}"})
    public List findByTerm(@PathVariable String term) {
        return repository.findByTitle(term);
    }

    @GetMapping(path = {"/artifact/{id}"})
    public List findByArtifactId(@PathVariable long id) {
        return repository.findByArtifact(id);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Howto> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Howto create(@RequestBody Howto howto){
        return repository.save(howto);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
package org.scijava.howto.api.controller;

import org.scijava.howto.api.model.AvailableArtifact;
import org.scijava.howto.api.repository.AvailableArtifactRepository;
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
@RequestMapping({"/api/sources"})
public class AvailableArtifactController {

  private AvailableArtifactRepository repository;

  AvailableArtifactController(AvailableArtifactRepository availableArtifactRepository) {
      this.repository = availableArtifactRepository;
  }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<AvailableArtifact> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"/{group}/{artifact}"})
    public ResponseEntity<AvailableArtifact> findByArtifact(@PathVariable String group, @PathVariable String artifact){
        return repository.findByArtifact(group, artifact)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AvailableArtifact create(@RequestBody AvailableArtifact artifact){
        return repository.save(artifact);
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
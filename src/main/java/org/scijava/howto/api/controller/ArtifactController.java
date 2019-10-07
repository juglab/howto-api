package org.scijava.howto.api.controller;

import org.scijava.howto.api.model.Artifact;
import org.scijava.howto.api.repository.ArtifactRepository;
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
@RequestMapping({"/api/"})
public class ArtifactController {

  private ArtifactRepository repository;

  ArtifactController(ArtifactRepository artifactRepository) {
      this.repository = artifactRepository;
  }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{group}/{artifact}"})
    public List findByArtifact(@PathVariable String group, @PathVariable String artifact){
        return repository.findByArtifact(group, artifact);
    }

    @GetMapping(path = {"/{group}/{artifact}/{version}"})
    public List findByVersionedArtifact(@PathVariable String group, @PathVariable String artifact, @PathVariable String version){
        return repository.findByArtifact(group, artifact, version);
    }

    @PostMapping
    public Artifact create(@RequestBody Artifact artifact){
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
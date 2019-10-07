package org.scijava.howto.api.controller;

import org.scijava.howto.api.model.Tag;
import org.scijava.howto.api.repository.TagRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/api/tags"})
public class TagController {

  private TagRepository repository;

  TagController(TagRepository tagRepository) {
      this.repository = tagRepository;
  }

    @GetMapping
    public List findAll(){
        List<Tag> all = repository.findAll();
        List<Tag> res = new ArrayList<>();
        all.stream().filter(tag -> all.stream().noneMatch(t -> t.getChildTags().contains(tag))).forEach(res::add);
        return res;
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Tag> findById(@PathVariable long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag){
        return repository.save(tag);
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
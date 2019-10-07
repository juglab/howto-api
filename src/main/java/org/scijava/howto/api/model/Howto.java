package org.scijava.howto.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Howto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String content;

  private String author;

  @ManyToOne
  @JoinColumn(name = "artifact", referencedColumnName = "id")
  private Artifact artifact;

//  @ManyToMany(cascade = CascadeType.ALL, mappedBy="howtos")
//  private Set<Tag> tags;

}
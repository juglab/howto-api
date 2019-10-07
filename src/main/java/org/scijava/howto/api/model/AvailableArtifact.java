package org.scijava.howto.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class AvailableArtifact {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String groupId;
  private String artifactId;
  private Boolean hasHowtos;

}
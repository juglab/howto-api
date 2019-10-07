package org.scijava.howto.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  public Set<Tag> getChildTags() {
    return childTags;
  }

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_tag_id")
  private Set<Tag> childTags;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(
          name = "howto_tag",
          joinColumns = { @JoinColumn(name = "tag_id") },
          inverseJoinColumns = { @JoinColumn(name = "howto_id") }
  )
  private Set<Howto> howtos;
}
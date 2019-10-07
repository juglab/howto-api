package org.scijava.howto.api.repository;

import org.scijava.howto.api.model.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtifactRepository
    extends JpaRepository<Artifact, Long> {

	@Query("SELECT t FROM Artifact t where t.availableArtifact.groupId = ?1 and t.availableArtifact.artifactId = ?2")
	List findByArtifact(String group, String artifact);

	@Query("SELECT t FROM Artifact t where t.version = ?3 and t.availableArtifact.groupId = ?1 and t.availableArtifact.artifactId = ?2")
	List findByArtifact(String group, String artifact, String version);
}
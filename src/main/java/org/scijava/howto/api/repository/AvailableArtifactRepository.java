package org.scijava.howto.api.repository;

import org.scijava.howto.api.model.AvailableArtifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvailableArtifactRepository
    extends JpaRepository<AvailableArtifact, Long> {
	@Query("SELECT t FROM AvailableArtifact t where t.groupId = ?1 and t.artifactId = ?2")
	Optional<AvailableArtifact> findByArtifact(String group, String artifact);
}
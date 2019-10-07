package org.scijava.howto.api.repository;

import org.scijava.howto.api.model.Howto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HowtoRepository
    extends JpaRepository<Howto, Long> {

	@Query("SELECT t FROM Howto t where t.title like %?1%")
	List<Howto> findByTitle(String term);

	@Query("SELECT t FROM Howto t where t.artifact.id = ?1")
	List findByArtifact(long id);
}
package org.scijava.howto.api.repository;

import org.scijava.howto.api.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository
    extends JpaRepository<Tag, Long> {
//	@Query("SELECT t FROM Tag t where t.parentTag IS NULL")
//	List findAllWithoutParent();
}
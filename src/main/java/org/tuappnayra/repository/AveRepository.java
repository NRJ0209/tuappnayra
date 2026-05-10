package org.tuappnayra.repository;

import org.tuappnayra.model.Ave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AveRepository extends JpaRepository<Ave, Long> {
}
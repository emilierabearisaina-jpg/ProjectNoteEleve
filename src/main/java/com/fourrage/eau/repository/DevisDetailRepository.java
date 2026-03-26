package com.fourrage.eau.repository;

import com.fourrage.eau.model.DevisDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevisDetailRepository extends JpaRepository<DevisDetail, Long> {
}

package com.clone.pinterest.repository;

import com.clone.pinterest.domain.Pin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinRepository extends JpaRepository<Pin, Long> {

}

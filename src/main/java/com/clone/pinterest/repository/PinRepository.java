package com.clone.pinterest.repository;

import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {

    Page<Pin> findAllByOrderByCreatedAtDesc(Pageable pageable);
    List<Pin> findAllByUser(User user);
    List<Pin> findByPinTitleContaining(String keyword);
}

package com.clone.pinterest.repository;

import com.clone.pinterest.domain.Pin;
import com.clone.pinterest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {

    List<Pin> findAllByOrderByCreatedAtDesc();
    List<Pin> findAllByUser(User user);
    List<Pin> findByPinTitleContaining(String keyword);
    Long countAllByBoard_BoardId(Long boardId);
    List<Pin> findTop5ByBoard_BoardId(Long boardId);

}

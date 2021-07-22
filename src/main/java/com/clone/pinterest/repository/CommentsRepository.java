package com.clone.pinterest.repository;

import com.clone.pinterest.domain.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    Page<Comments> findAllByPinId(Long pinId, Pageable pageable);
    Long countByPinId(Long pinId);
    void deleteAllByPinId(Long pinId);
}

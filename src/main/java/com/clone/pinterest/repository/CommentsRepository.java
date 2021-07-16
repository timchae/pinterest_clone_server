package com.clone.pinterest.repository;

import com.clone.pinterest.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findAllByPin_PinId(Long pinId);
}

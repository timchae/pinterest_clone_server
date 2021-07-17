package com.clone.pinterest.repository;



import com.clone.pinterest.domain.Liken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikenRepository extends JpaRepository<Liken, Long> {
    Liken findByCommentId(Long commentId);
    Long countByCommentId(Long commentId);
    boolean existsByCommentId(Long commentId);
}

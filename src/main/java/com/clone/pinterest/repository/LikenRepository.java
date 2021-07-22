package com.clone.pinterest.repository;



import com.clone.pinterest.domain.Liken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikenRepository extends JpaRepository<Liken, Long> {
    Liken findByCommentId(Long commentId);
    Long countByCommentId(Long commentId);
    boolean existsByCommentIdAndUserId(Long commentId, Long userId);
    void deleteAllByCommentId(Long commentId);
    Liken findByCommentIdAndUserId(Long commentId, Long userId);
    void deleteAllByPinId(Long pinId);
}

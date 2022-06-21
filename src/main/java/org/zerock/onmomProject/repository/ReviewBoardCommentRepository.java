package org.zerock.onmomProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.onmomProject.entity.ReviewBoardComment;

public interface ReviewBoardCommentRepository extends JpaRepository<ReviewBoardComment, Long> {
}
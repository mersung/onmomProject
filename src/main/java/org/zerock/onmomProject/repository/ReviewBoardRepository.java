package org.zerock.onmomProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.repository.search.SearchReviewBoardRepository;

public interface ReviewBoardRepository extends JpaRepository<ReviewBoard, Long>, SearchReviewBoardRepository {

}

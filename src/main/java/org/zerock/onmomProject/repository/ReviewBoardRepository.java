package org.zerock.onmomProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.repository.search.SearchReviewBoardRepository;

import java.util.List;

public interface ReviewBoardRepository extends JpaRepository<ReviewBoard, Long>, SearchReviewBoardRepository {
    @Query("select rb, m from ReviewBoard rb LEFT JOIN rb.member m where rb.review_id =:review_id")
    Object getReviewBoardWithMember(@Param("review_id") Long review_id);

    @Query("SELECT rb, rbc FROM ReviewBoard rb LEFT JOIN ReviewBoardComment rbc ON rbc.reviewBoard = rb WHERE rb.review_id = :review_id")
    List<Object[]> getReviewBoardWithReviewBoardComment(@Param("review_id") Long review_id);

    @Query(value = "select rb, m_id, count(fbc) " +
            " from ReviewBoard rb" +
            " left join rb.member m_id" +
            " left join ReviewBoardComment rbc ON rbc.reviewBoard = rb " +
            " group by rb",
            countQuery = "select count(rb) from ReviewBoard rb")
    Page<Object[]> getReviewBoardWithReviewBoardCommentCount(Pageable pageable);
}

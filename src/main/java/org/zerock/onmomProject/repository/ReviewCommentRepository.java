package org.zerock.onmomProject.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.entity.ReviewBoardComment;

import java.util.List;
public interface ReviewCommentRepository extends JpaRepository<ReviewBoardComment, Long> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ReviewBoardComment> findByReviewBoard(ReviewBoard reviewBoard);

    @Modifying
    @Query("DELETE FROM ReviewBoardComment rbc WHERE rbc.member = :member")
    void deleteByMember(String member);
}

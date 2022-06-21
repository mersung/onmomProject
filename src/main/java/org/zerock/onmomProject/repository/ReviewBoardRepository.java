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
    //한개의 로우(Object) 내에 Object[ ]로 나옴
    @Query("select b, w from review_board b left join b.member_id w where b.review_id =:review_id")
    Object getBoardWithWriter(@Param("review_id") Long review_id);

    @Query("select b, r from review_board b left join review_board_comment r on r.review_board = b where b.review_id = :review_id")
    List<Object[]> getBoardWithReply(@Param("review_id") Long review_id);

    @Query(value ="select b, w, count(r) " +
            " from review_board b " +
            " left join b.member_id w " +
            " left join review_board_comment r on r.review_board = b " +
            " group by b",
            countQuery = "select count(b) from review_board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable); //목록 화면에 필요한 데이터

    @Query("select b, w, count(r) " +
            " from review_board b left join b.member_id w " +
            " left outer join review_board_comment r on r.review_board = b" +
            " where b.review_id = :review_id")
    Object getBoardByBno(@Param("review_id")Long review_id);
}

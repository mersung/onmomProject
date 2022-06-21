package org.zerock.onmomProject.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.onmomProject.entity.QMember;
import org.zerock.onmomProject.entity.QReviewBoard;
import org.zerock.onmomProject.entity.QReviewBoardComment;
import org.zerock.onmomProject.entity.ReviewBoard;

import java.util.List;

@Log4j2
// 쿼리 메서드나 @Query등으로 처리할 수 없는 기능은 별도의 인터페이스로 설계
public class SearchReviewBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchReviewBoardRepository {

    public SearchReviewBoardRepositoryImpl(){
        super(ReviewBoard.class);
    }

    @Override
    public ReviewBoard search1() {
        log.info("search1..........");

        // Querydsl 라이브러리 내에는 JPQLQuery라는 인터페이스가 있다.
        QReviewBoard reviewBoard = QReviewBoard.reviewBoard; //엔티티 불러오기
        QReviewBoardComment reviewBoardComment = QReviewBoardComment.reviewBoardComment; //엔티티 불러오기
        QMember member = QMember.member;

        JPQLQuery<ReviewBoard> jpqlQuery = from(reviewBoard);
        jpqlQuery.leftJoin(member).on(reviewBoard.member_id.eq(member));
        jpqlQuery.leftJoin(reviewBoardComment).on(reviewBoardComment.review_id.eq(reviewBoard));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(reviewBoard, member.member_id, reviewBoardComment.review_id
        , reviewBoardComment.content);
        tuple.groupBy(reviewBoard);

        jpqlQuery.select(reviewBoard).where(reviewBoard.review_id.eq(1L));
        List<Tuple> result = tuple.fetch();
        return null;
    }
}

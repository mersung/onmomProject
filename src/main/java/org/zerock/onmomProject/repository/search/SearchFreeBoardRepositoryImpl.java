package org.zerock.onmomProject.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.onmomProject.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
// 쿼리 메서드나 @Query등으로 처리할 수 없는 기능은 별도의 인터페이스로 설계
public class SearchFreeBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchFreeBoardRepository {

    public SearchFreeBoardRepositoryImpl(){
        super(ReviewBoard.class);
    }

    @Override
    public List<ReviewBoard> search1() {
        return null;
    }

    @Override
    public Page<Object[]> FreeSearchPage(String type, String keyword, Pageable pageable) {
        // area는 지역, type은 제목/내용/제목+내용, keyword는 단어
        log.info("search1..........");

        // Querydsl 라이브러리 내에는 JPQLQuery라는 인터페이스가 있다.
        QFreeBoard freeBoard = QFreeBoard.freeBoard; //엔티티 불러오기
        QFreeBoardComment freeBoardComment = QFreeBoardComment.freeBoardComment; //엔티티 불러오기
        QMember member = QMember.member;


        JPQLQuery<FreeBoard> jpqlQuery = from(freeBoard);
        jpqlQuery.leftJoin(member).on(freeBoard.member.eq(member));
        jpqlQuery.leftJoin(freeBoardComment).on(freeBoardComment.board.eq(freeBoard));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(freeBoard, member.member_id, freeBoardComment.board
        , freeBoardComment.content);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = freeBoard.free_id.gt(0L);
        booleanBuilder.and(expression);

        if(type != null){
            String[] typeArr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for(String t:typeArr){
                switch (t){
                    case "t":
                        conditionBuilder.or(freeBoard.title.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(member.member_id.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(freeBoard.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        tuple.where(booleanBuilder);

        Sort sort = pageable.getSort();

        sort.stream().forEach(order -> {
            Order direction = order.isAscending()? Order.ASC: Order.DESC;
            String prop = order.getProperty();

            PathBuilder orderByExpression = new PathBuilder(ReviewBoard.class, "freeBoard");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });

        tuple.groupBy(freeBoard);

        //page처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        long count = tuple.fetchCount();

        return new PageImpl<Object[]>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }


}

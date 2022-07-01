package org.zerock.onmomProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.repository.search.SearchFreeBoardRepository;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long>, SearchFreeBoardRepository {

    @Query("select fb, m from FreeBoard fb LEFT JOIN fb.member m where fb.free_id =:free_id")
    Object getFreeBoardWithMember(@Param("free_id") Long free_id);

    @Query("SELECT fb, fbc FROM FreeBoard fb LEFT JOIN FreeBoardComment fbc ON fbc.board = fb WHERE fb.free_id = :free_id")
    List<Object[]> getFreeBoardWithFreeBoardComment(@Param("free_id") Long free_id);

    @Query(value = "select fb, m_id, count(fbc) " +
                    " from FreeBoard fb" +
                    " left join fb.member m_id" +
                    " left join FreeBoardComment fbc ON fbc.board = fb " +
                    " group by fb",
                    countQuery = "select count(fb) from FreeBoard fb")
    Page<Object[]> getFreeBoardWithFreeBoardCommentCount(Pageable pageable);

    @Query("select b, m, count(fbc)" +
            "from FreeBoard b left join b.member m" +
            " left outer join FreeBoardComment fbc on fbc.board = b"+
            " where b.free_id = :free_id")
    Object getFreeBoardByFree_id(@Param("free_id")Long free_id);

    // 멤버 아이디로 마이페이지 구현
    @Query(value = "select m, fb, count(fbc) " +
            " from FreeBoard fb" +
            " left join fb.member m_id" +
            " left join FreeBoardComment fbc ON fbc.board = fb " +
            " where fb.member.member_id = :member_id " +
            " group by fb",
            countQuery = "select count(fb) from FreeBoard fb where fb.member.member_id = :member_id")
    Page<Object[]> getMyPostByMember_id(@Param("member_id")String member_id, Pageable pageable);

}

package org.zerock.onmomProject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.onmomProject.entity.FreeBoard;

import java.util.List;

public interface        FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

    @Query("select fb, m_id from FreeBoard fb left join fb.member_id m_id where fb.free_id =: free_id")
    Object getFreeBoardWithMember_id(@Param("free_id") Long free_id);

    @Query("SELECT fb, fbc FROM FreeBoard fb LEFT JOIN FreeBoardComment fbc ON fbc.board_id = fb WHERE fb.free_id = :free_id")
    List<Object[]> getFreeBoardWithFreeBoardComment(@Param("free_id") Long free_id);

    @Query(value = "select fb,m_id count(fbc)" +
                    "from FreeBoard fb" +
                    "left join fb.member_id m_id" +
                    "left join FreeBoardComment fbc ON fbc.board_id = fb" +
                    "group by fb",
                    countQuery = "select count(fb) from FreeBoard fb")
    Page<Object[]> getFreeBoardWithFreeBoardCommentCount(Pageable pageable);
}

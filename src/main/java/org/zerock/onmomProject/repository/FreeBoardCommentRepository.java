package org.zerock.onmomProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.FreeBoardComment;

import java.util.List;

public interface FreeBoardCommentRepository extends JpaRepository<FreeBoardComment, Long> {

    @Modifying
    @Query("delete from FreeBoardComment fbc where fbc.comment_id = :free_id")
    void deleteByFree_id(Long free_id);

//    List<FreeBoardComment> getRepliesByBoardOrderByFree_id(FreeBoard freeBoard);
}

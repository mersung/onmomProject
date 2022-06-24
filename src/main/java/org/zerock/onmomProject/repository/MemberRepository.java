package org.zerock.onmomProject.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.onmomProject.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
//    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
//    @Query("select m from Member m where m.member_id =:member_id")
//    Optional<Member> findByEmail(@Param("member_id") String member_id);

}
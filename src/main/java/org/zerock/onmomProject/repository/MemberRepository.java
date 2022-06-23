package org.zerock.onmomProject.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.onmomProject.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {


}
package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @EntityGraph("memberWithDetails")
    @Query("select m from Member m")
    List<Member> getAllBy();

}

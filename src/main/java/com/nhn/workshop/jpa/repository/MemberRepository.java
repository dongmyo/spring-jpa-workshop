package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // TODO: N + 1 문제를 Fetch Join으로 해결하세요.
    @Query("select m from Member as m")
    List<Member> getMembersWithDetails();

}

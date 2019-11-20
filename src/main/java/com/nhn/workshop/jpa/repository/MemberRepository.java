package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // TODO : @EntityGraph로 설정한 Entity Graph를 이용
    @Query("select m from Member m")
    List<Member> getAllBy();

}

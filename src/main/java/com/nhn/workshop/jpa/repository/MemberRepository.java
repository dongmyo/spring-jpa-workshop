package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

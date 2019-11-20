package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.entity.MemberDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, Long> {
}

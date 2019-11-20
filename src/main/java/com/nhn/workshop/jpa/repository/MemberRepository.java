package com.nhn.workshop.jpa.repository;

import com.nhn.workshop.jpa.dto.MemberIdGetter;
import com.nhn.workshop.jpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @EntityGraph("memberWithDetails")
    @Query("select m from Member m")
    List<Member> getAllBy();

    // NOTE: #1 Pagination 쿼리로 ID만 가져온다
    Page<MemberIdGetter> findAllBy(Pageable pageable);

    // TODO: #2 ID 조건에 해당하는 Entity들에만 Fetch JOIN 적용
    @Query("select m from Member as m left join fetch m.details as md")
    List<Member> getMemberWithAssociations(Collection<Long> memberIds);

}

package com.nhn.workshop.jpa.service;

import com.nhn.workshop.jpa.entity.Member;
import com.nhn.workshop.jpa.entity.MemberDetail;
import com.nhn.workshop.jpa.repository.MemberDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class MemberService {
    private final MemberDetailRepository memberDetailRepository;


    public MemberService(MemberDetailRepository memberDetailRepository) {
        this.memberDetailRepository = memberDetailRepository;
    }


    @Transactional
    public void createMemberWithDetails() {
        Member member = new Member();
        member.setName("member1");
        member.setCreateDate(LocalDateTime.now());

        MemberDetail memberDetail1 = new MemberDetail();
        memberDetail1.setMember(member);
        memberDetail1.setType("type1");
        memberDetail1.setDescription("member1-type1");

        MemberDetail memberDetail2 = new MemberDetail();
        memberDetail2.setMember(member);
        memberDetail2.setType("type2");
        memberDetail2.setDescription("member1-type2");

        memberDetailRepository.saveAll(Arrays.asList(memberDetail1, memberDetail2));
    }

}

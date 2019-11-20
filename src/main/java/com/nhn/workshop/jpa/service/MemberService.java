package com.nhn.workshop.jpa.service;

import com.nhn.workshop.jpa.entity.Member;
import com.nhn.workshop.jpa.entity.MemberDetail;
import com.nhn.workshop.jpa.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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

        member.getDetails().add(memberDetail1);
        member.getDetails().add(memberDetail2);

        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("member2");
        member2.setCreateDate(LocalDateTime.now());

        MemberDetail memberDetail3 = new MemberDetail();
        memberDetail3.setMember(member2);
        memberDetail3.setType("type1");
        memberDetail3.setDescription("member2-type1");

        member2.getDetails().add(memberDetail3);

        memberRepository.save(member2);
    }

    @Transactional(readOnly = true)
    public List<String> getAllMemberDescriptions() {
        return memberRepository.getAllBy()
                               .stream()
                               .map(Member::getDetails)
                               .flatMap(Collection::stream)
                               .map(MemberDetail::getDescription)
                               .filter(Objects::nonNull)
                               .collect(Collectors.toList());
    }

}

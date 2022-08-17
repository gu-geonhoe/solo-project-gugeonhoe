package com.example.solopr.api.v1.controller;

import com.example.solopr.api.v1.entity.CompanyLocation;
import com.example.solopr.api.v1.entity.CompanyType;
import com.example.solopr.api.v1.entity.Member;
import com.example.solopr.api.v1.entity.MemberResponse;
import com.example.solopr.api.v1.repository.CompanyRepository;
import com.example.solopr.api.v1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/members")
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;

    @GetMapping
    @Transactional
    public List<MemberResponse> members(@RequestParam(required = false) String type, @RequestParam(required = false) String location) {

        log.info("members type={}, location={}",type,location);

        List<Member> members;
        if (type != null && location != null) {
            CompanyType companyType = companyRepository.findCompanyTypeByName(type);
            CompanyLocation companylocation = companyRepository.findCompanyLocationByName(location);
            members = memberRepository.findAllByCompanyTypeAndCompanyLocation(companyType, companylocation);
        } else if (type != null) {
            CompanyType companyType = companyRepository.findCompanyTypeByName(type);
            members = memberRepository.findAllByCompanyType(companyType);
        } else if (location != null) {
            CompanyLocation companylocation = companyRepository.findCompanyLocationByName(location);
            members = memberRepository.findAllByCompanyLocation(companylocation);
        } else {
            members = memberRepository.findAll();
        }

        return members.stream().map(MemberResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/init")
    @Transactional
    public String init(){
        CompanyType it = new CompanyType("IT");

        CompanyType bakery = new CompanyType("bakery");

        CompanyType mart = new CompanyType("mart");

        companyRepository.save(it, bakery, mart);

        CompanyLocation seoul = new CompanyLocation("서울");

        CompanyLocation daegu = new CompanyLocation("대구");

        CompanyLocation jeju = new CompanyLocation("제주");

        companyRepository.save(seoul, daegu, jeju);

        memberRepository.save(new Member("member1", "1234", "MALE", "Company1", bakery, daegu));
        memberRepository.save(new Member("member2", "1234", "MALE", "Company2", mart, daegu));
        memberRepository.save(new Member("member3", "1234", "MALE", "Company3", it, seoul));
        memberRepository.save(new Member("member4", "1234", "FEMALE", "Company4", it, jeju));
        memberRepository.save(new Member("member5", "1234", "MALE", "Company5", bakery, seoul));

        return "hi";
    }

}

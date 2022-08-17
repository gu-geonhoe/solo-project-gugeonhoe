package com.example.solopr.api.v1.repository;

import com.example.solopr.api.v1.entity.CompanyLocation;
import com.example.solopr.api.v1.entity.CompanyType;
import com.example.solopr.api.v1.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findAllByCompanyLocation(CompanyLocation location);
    List<Member> findAllByCompanyType(CompanyType location);
    List<Member> findAllByCompanyTypeAndCompanyLocation(CompanyType type, CompanyLocation location);
}

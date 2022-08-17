package com.example.solopr.api.v1.entity;

import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;
    private String name;
    private String gender;
    private String companyName;
    private String companyType;
    private String companyLocation;

    public MemberResponse(Member member){
        this.id = member.getId();
        this.name = member.getName();
        this.gender = member.getSex();
        this.companyName = member.getCompanyName();
        this.companyType = member.getCompanyType().getName();
        this.companyLocation = member.getCompanyLocation().getName();
    }

}
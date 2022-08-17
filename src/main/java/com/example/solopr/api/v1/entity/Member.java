package com.example.solopr.api.v1.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;


    private String  sex;

    private String companyName;

    @ManyToOne
    @JoinColumn(name="company_type")
    private CompanyType companyType;

    @ManyToOne
    @JoinColumn(name="company_location")
    private CompanyLocation companyLocation;

    public Member(String name, String password, String  sex, String companyName, CompanyType companyType, CompanyLocation companyLocation) {
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.companyName = companyName;
        this.companyType = companyType;
        this.companyLocation = companyLocation;
    }


}

package com.example.solopr.api.v1.repository;

import com.example.solopr.api.v1.entity.CompanyLocation;
import com.example.solopr.api.v1.entity.CompanyType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {

    private final EntityManager em;

    public CompanyType findCompanyTypeByName(String name){
        return em.createQuery("SELECT t FROM CompanyType t WHERE t.name = :name", CompanyType.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public CompanyLocation findCompanyLocationByName(String name){
        return em.createQuery("SELECT l FROM CompanyLocation l WHERE l.name = :name", CompanyLocation.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public void save(Object... objects){
        for (Object object : objects) {
            em.persist(object);
        }
    }
}
package com.springboot.theara.service.specification;

import com.springboot.theara.entity.CourseMaterial;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CourseMaterialSpecification {
    public static Specification<CourseMaterial> urlCourseMaterial(String url)
    {
        return new Specification<CourseMaterial>() {
            @Override
            public Predicate toPredicate(Root<CourseMaterial> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("url"),"%"+url+"%");
            }
        };
    }
    public static Specification<CourseMaterial> idCourseMaterial(String courseMaterialId)
    {
        return new Specification<CourseMaterial>() {
            @Override
            public Predicate toPredicate(Root<CourseMaterial> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                long a=0;
                try
                {
                    a=Long.parseLong(courseMaterialId);
                }catch (NumberFormatException e){}
                return criteriaBuilder.equal(root.get("courseMaterialId"),a);
            }
        };
    }

}

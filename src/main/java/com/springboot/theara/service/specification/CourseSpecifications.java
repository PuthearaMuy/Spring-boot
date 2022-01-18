package com.springboot.theara.service.specification;

import com.springboot.theara.entity.Course;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CourseSpecifications {
    public static Specification<Course> titleCourse(String title)
    {
        return new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("title"),"%"+title+"%");
            }
        };
    }
    public static Specification<Course> creditCourse(String credit)
    {
        return new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                int a=0;
                try {
                    a=Integer.parseInt(credit);
                }catch (NumberFormatException e){}
                return criteriaBuilder.equal(root.get("credit"),a);
            }
        };
    }
    public static Specification<Course> idCourse(String id)
    {
        return new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                long a=0;
                try {
                    a=Long.parseLong(id);
                }catch (NumberFormatException e){}
                return criteriaBuilder.equal(root.get("courseId"),a);
            }
        };
    }
}

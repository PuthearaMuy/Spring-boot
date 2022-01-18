package com.springboot.theara.service.specification;

import com.springboot.theara.entity.Teacher;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TeacherSpecification {
    public static Specification<Teacher> firstNameTeacher(String firstName)
    {
        return new Specification<Teacher>() {
            @Override
            public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("firstName"),"%"+firstName+"%");
            }
        };
    }
    public static Specification<Teacher> lastNameTeacher(String lastName)
    {
        return new Specification<Teacher>() {
            @Override
            public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("lastName"),"%"+lastName+"%");
            }
        };
    }
    public static Specification<Teacher> idTeacher(String id)
    {
        return new Specification<Teacher>() {
            @Override
            public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                long a=0;
                try
                {
                    a=Long.parseLong(id);
                }catch (NumberFormatException e){}
                return criteriaBuilder.equal(root.get("teacherId"),a);
            }
        };
    }
}

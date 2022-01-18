package com.springboot.theara.service.specification;

import com.springboot.theara.entity.Student;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StudentSpecification {
    public static Specification<Student> firstNameStudent(String firstName)
    {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("firstName"),"%"+firstName+"%");
            }
        };
    }
    public static Specification<Student> lastNameStudent(String lastName)
    {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("lastName"),"%"+lastName+"%");
            }
        };
    }
    public static Specification<Student> email(String email)
    {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("emailId"),"%"+email+"%");
            }
        };
    }
    public static Specification<Student> studentId(String id)
    {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                long a=0;
                try
                {
                    a=Long.parseLong(id);
                }catch(NumberFormatException e){}
                return criteriaBuilder.equal(root.get("studentId"),a);
            }
        };
    }
}

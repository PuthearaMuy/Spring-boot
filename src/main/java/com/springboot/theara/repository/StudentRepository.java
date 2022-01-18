package com.springboot.theara.repository;

import com.springboot.theara.dto.projection.StudentProjection;
import com.springboot.theara.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> , JpaSpecificationExecutor {
    List<StudentProjection> findAllProjectedBy();
}

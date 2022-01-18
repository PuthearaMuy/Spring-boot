package com.springboot.theara.repository;

import com.springboot.theara.dto.projection.TeacherProjection;
import com.springboot.theara.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> , JpaSpecificationExecutor<Teacher> {
    List<TeacherProjection> findAllProjectedBy();
}

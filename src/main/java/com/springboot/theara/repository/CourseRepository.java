package com.springboot.theara.repository;

import com.springboot.theara.dto.projection.CourseProjection;
import com.springboot.theara.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> ,JpaSpecificationExecutor<Course> {
    List<CourseProjection> findAllProjectedBy();
}

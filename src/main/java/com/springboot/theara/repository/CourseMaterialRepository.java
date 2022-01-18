package com.springboot.theara.repository;

import com.springboot.theara.dto.projection.CourseMaterialProjection;
import com.springboot.theara.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial,Long> , JpaSpecificationExecutor<CourseMaterial> {
    List<CourseMaterialProjection> findAllProjectedBy();
}

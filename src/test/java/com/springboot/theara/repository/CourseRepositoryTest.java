package com.springboot.theara.repository;

import com.springboot.theara.dto.projection.CourseProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;
    @Test
    public void select()
    {
        List<CourseProjection> list= repository.findAllProjectedBy();
        System.out.println(list);
    }




}
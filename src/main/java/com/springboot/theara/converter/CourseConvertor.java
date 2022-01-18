package com.springboot.theara.converter;

import com.springboot.theara.dto.CourseDto;
import com.springboot.theara.entity.Course;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseConvertor {
    @Autowired
    ModelMapper map;
    public CourseDto entityToDto(Course course)
    {
        CourseDto courseDto=map.map(course,CourseDto.class);
        return courseDto;
    }
    public Course dtoToEntity(CourseDto dto)
    {
        Course course=map.map(dto,Course.class);
        return course;
    }
    public List<CourseDto> entityToDto(List<Course> course)
    {
        return course.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }
    public List<Course> dtoToEntity(List<CourseDto> dto)
    {
        return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }

}

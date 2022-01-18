package com.springboot.theara.converter;

import com.springboot.theara.dto.TeacherDto;
import com.springboot.theara.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeacherConvert {
    @Autowired
    private ModelMapper map;
    public TeacherDto entityToDto(Teacher teacher)
    {
//        TeacherDto dto=new TeacherDto();
//        dto.setFirstName(teacher.getFirstName());
//        dto.setLastName(teacher.getLastName());
//        dto.setCourse(teacher.getCourse());
//        dto.setTeacherId(teacher.getTeacherId());
        //System.out.println(teacher.getCourse());
        TeacherDto dto=map.map(teacher,TeacherDto.class);
        return dto;
    }
    public Teacher dtoToEntity(TeacherDto dto)
    {
//        Teacher teacher=new Teacher();
//        teacher.setFirstName(dto.getFirstName());
//        teacher.setLastName(dto.getLastName());
//        teacher.setCourse(dto.getCourse());
//        teacher.setTeacherId(dto.getTeacherId());
        Teacher teacher=map.map(dto,Teacher.class);
        return teacher;
    }

    public List<TeacherDto> entityToDto(List<Teacher> teacher)
    {
        return teacher.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }
    public List<Teacher> dtoToEntity(List<TeacherDto> dto)
    {
        return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }
}

package com.springboot.theara.converter;

import com.springboot.theara.dto.StudentDto;
import com.springboot.theara.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConvert {
    @Autowired
    private ModelMapper map;
    public StudentDto entityToDto(Student student)
    {
//        StudentDto dto=new StudentDto();
//        dto.setId(student.getStudentId());
//        dto.setFirstName(student.getFirstName());
//        dto.setLastName(student.getLastName());
//        dto.setEmail(student.getEmailId());
//        dto.setGuardianName(student.getGuardian().getGuardianName());
//        dto.setGuardianEmail(student.getGuardian().getGuardianEmail());
//        dto.setGuardianMobile(student.getGuardian().getGuardianMobile());
        StudentDto dto=map.map(student,StudentDto.class);
        return dto;
    }
    public Student dtoToEntity(StudentDto dto)
    {
//        Student student=new Student();
//        Guardian guardian=new Guardian();
//        student.setStudentId(dto.getId());
//        student.setFirstName(dto.getFirstName());
//        student.setLastName(dto.getLastName());
//        student.setEmailId(dto.getEmail());
//        guardian.setGuardianName(dto.getGuardianName());
//        guardian.setGuardianEmail(dto.getGuardianEmail());
//        guardian.setGuardianMobile(dto.getGuardianMobile());
//        student.setGuardian(guardian);
        Student student=map.map(dto,Student.class);
        return student;
    }
    public List<StudentDto> entityToDto(List<Student> students)
    {
        return students.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }
    public List<Student> dtoToEntity(List<StudentDto> dto)
    {
        return dto.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }
}

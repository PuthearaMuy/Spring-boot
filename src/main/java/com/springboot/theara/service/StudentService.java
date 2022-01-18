package com.springboot.theara.service;

import com.springboot.theara.converter.StudentConvert;
import com.springboot.theara.dto.StudentDto;
import com.springboot.theara.dto.projection.StudentProjection;
import com.springboot.theara.entity.Guardian;
import com.springboot.theara.entity.Student;
import com.springboot.theara.exceptions.DeleteException;
import com.springboot.theara.exceptions.NotFoundException;
import com.springboot.theara.repository.StudentRepository;
import com.springboot.theara.service.specification.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService implements com.springboot.theara.service.Service {
    @Autowired
    private StudentRepository studentRepository;
    /**
     * User for convert Student Entity to StudentDto
     * StudentDto to Student Entity
     */
    @Autowired
    private StudentConvert convert;

    /**
     * Save Student information to database
     * @param dto StudentDto
     * @return StudentDto
     */
    public StudentDto save(StudentDto dto)
    {
        Student student=studentRepository.save(convert.dtoToEntity(dto));
        return convert.entityToDto(student);
    }

    /**
     * Get All Student Projection
     * @return List Student Projection
     */
    public List<StudentProjection> selectAllProjection()
    {
        return studentRepository.findAllProjectedBy();
    }

    /**
     * Get all student from database
     * @return List StudentDto
     */
    @Override
    public List<StudentDto> selectAll() {

        List<Student> student=studentRepository.findAll();
        return convert.entityToDto(student);
    }

    /**
     * Get Specific Student by StudentId
     * @param id StudentId
     * @return StudentDto
     */
    public StudentDto selectById(Long id) {
        Student student=studentRepository.findById(id).orElseThrow(()->new NotFoundException("Student not found"));
        return convert.entityToDto(student);
    }

    /**
     * Delete All Student in database
     * @return
     */
    @Override
    public String deleteAll() {
        studentRepository.deleteAll();
        return "Delete all Student.";
    }

    /**
     * Delete specific student by id
     * @param id StudentId
     * @return
     */
    @Override
    public String deleteById(Long id) {
        studentRepository.findById(id).orElseThrow(()->new DeleteException("StudentId not found. No value to delete."));
        studentRepository.deleteById(id);
        return "Delete :"+id;
    }

    /**
     * Update Student Information By StudentId
     * @param id StudentId
     * @param dto StudentDto
     * @return StudentDto
     */
    public StudentDto update(Long id,StudentDto dto)
    {
        Student student=studentRepository.findById(id).orElseThrow(()->new NotFoundException("Student not found"));
        if(dto.getFirstName()!=null)
        student.setFirstName(dto.getFirstName());
        if(dto.getLastName()!=null)
        student.setLastName(dto.getLastName());
        if(dto.getEmailId()!=null)
        student.setEmailId(dto.getEmailId());
        Guardian guardian=new Guardian();
        if(dto.getGuardianName()!=null)
        guardian.setGuardianName(dto.getGuardianName());
        if(dto.getGuardianMobile()!=null)
        guardian.setGuardianMobile(dto.getGuardianMobile());
        if(dto.getGuardianEmail()!=null)
        guardian.setGuardianEmail(dto.getGuardianEmail());
        if(guardian.equals(null))
        student.setGuardian(guardian);
        studentRepository.save(student);
        return convert.entityToDto(student);
    }
    /**
     * Get Student by Pagination and Specification filter
     * @param filter (StudentFirstName,StudentLastName,Email,StudentId) allow null
     * @param page Pageable allow null
     * @return List StudentDto
     */
    public List<StudentDto> findByPageable(String filter,Pageable page) {
        List<Student> list = studentRepository.findAll(StudentSpecification.firstNameStudent(filter).or(StudentSpecification.lastNameStudent(filter)).or(StudentSpecification.email(filter)).or(StudentSpecification.studentId(filter)),page).getContent();
        return convert.entityToDto(list);
    }
}

package com.springboot.theara.service;

import com.springboot.theara.converter.TeacherConvert;
import com.springboot.theara.dto.TeacherDto;
import com.springboot.theara.dto.projection.TeacherProjection;
import com.springboot.theara.entity.Teacher;
import com.springboot.theara.exceptions.DeleteException;
import com.springboot.theara.exceptions.NotFoundException;
import com.springboot.theara.repository.TeacherRepository;
import com.springboot.theara.service.specification.TeacherSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TeacherService implements com.springboot.theara.service.Service {

    @Autowired
    private TeacherRepository teacherRepository;
    /**
     *
     */
    @Autowired
    private TeacherConvert convert;

    /**
     * Save Teacher in to database
     * @param dto TeacherDto
     * @return TeacherDto
     */
    public TeacherDto save(@RequestBody TeacherDto dto)
    {
        Teacher teacher=teacherRepository.save(convert.dtoToEntity(dto));
        return convert.entityToDto(teacher);
    }

    /**
     * Get All Teacher Projection
     * @return List Teacher Projection
     */
    public List<TeacherProjection> selectByProjection()
    {
        return teacherRepository.findAllProjectedBy();
    }

    /**
     * Get All Teacher information from database
     * @return List TeacherDto
     */
    @Override
    public List<TeacherDto> selectAll() {
        List<Teacher> list=teacherRepository.findAll();

        return convert.entityToDto(list);
    }

    /**
     * Get specific Teacher by id
     * @param id TeacherId
     * @return TeacherDto
     */
    public TeacherDto selectById(Long id) {
        Teacher teacher=teacherRepository.findById(id).orElseThrow(()->new NotFoundException("Teacher not found"));
        return convert.entityToDto(teacher);
    }

    /**
     * Delete all teacher from database
     */
    @Override
    public String deleteAll() {
        teacherRepository.deleteAll();
        return "Delete all teacher!!!";
    }

    /**
     * Delete specific Teacher by id
     * @param id TeacherId
     * @return information text
     */
    @Override
    public String deleteById(Long id) {
        teacherRepository.findById(id).orElseThrow(()->new DeleteException("teacherId not match.No Data to Delete"));
        teacherRepository.deleteById(id);
        return "Delete :"+id;
    }

    /**
     * Update specific Teacher by id
     * @param id TeacherId
     * @param teacher TeacherDto
     * @return TeacherDto
     */
    public TeacherDto update(Long id, TeacherDto teacher) {
        Teacher teacher1=teacherRepository.findById(id).orElseThrow(()->new NotFoundException("Teacher not found."));
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setLastName(teacher.getLastName());
        teacherRepository.save(teacher1);
        return convert.entityToDto(teacher1);
    }

    /**
     * Get Teacher by Pagination and Specification filter
     * @param filter (TeacherFirstName,TeacherLastName,TeacherId) allow null
     * @param pageable Pageable
     * @return List TeacherDto
     */
    public List<TeacherDto> findByPageable(String filter,Pageable pageable)
    {
        List<Teacher> list=teacherRepository.findAll(TeacherSpecification.firstNameTeacher(filter).or(TeacherSpecification.lastNameTeacher(filter)).or(TeacherSpecification.idTeacher(filter)),pageable).getContent();
        return convert.entityToDto(list);
    }

}

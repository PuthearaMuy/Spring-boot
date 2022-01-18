package com.springboot.theara.service;

import com.springboot.theara.converter.CourseConvertor;
import com.springboot.theara.dto.CourseDto;
import com.springboot.theara.dto.projection.CourseProjection;
import com.springboot.theara.entity.Course;
import com.springboot.theara.entity.Student;
import com.springboot.theara.exceptions.DeleteException;
import com.springboot.theara.exceptions.InvalidDataAccessException;
import com.springboot.theara.exceptions.NotFoundException;
import com.springboot.theara.repository.CourseRepository;
import com.springboot.theara.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.springboot.theara.service.specification.CourseSpecifications.titleCourse;
import static com.springboot.theara.service.specification.CourseSpecifications.creditCourse;
import static com.springboot.theara.service.specification.CourseSpecifications.idCourse;

@org.springframework.stereotype.Service
public class CourseService implements Service {
    @Autowired
    private CourseConvertor convertor;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    /**
     * save only course to database
     * @param dto courseDto (title,credit)
     * @return courseDto
     */
    public CourseDto save(CourseDto dto)
    {
        if(dto.getTeacherId()!=null)
            throw new InvalidDataAccessException("can't save teacherId try to save without teacherId");
        if(dto.getStudentId()!=null)
            throw new InvalidDataAccessException("Can't'save with studentId");
        Course course=courseRepository.save(convertor.dtoToEntity(dto));
        return convertor.entityToDto(course);
    }

    /**
     * Save course with studentId
     * @param dto courseDto with studentId
     * @return courseDto
     */
    public CourseDto saveWithStudent(CourseDto dto)
    {
        if(dto.getTeacherId()==null)
            throw new InvalidDataAccessException("can't save with teacherId");
        Student student=studentRepository.findById(dto.getStudentId()).orElseThrow(()->new NotFoundException("StudentID not found"));
        Course course=convertor.dtoToEntity(dto);
        course.addStudent(student);
        courseRepository.save(course);
        return convertor.entityToDto(course);
    }

    /**
     * Select all Course
     * @return list courseDto
     */
    public List<CourseDto> selectAll() {
        List<Course> list=courseRepository.findAll();
        return convertor.entityToDto(list);
    }


    /**
     * Use for select only course projection
     * @return courseProjection
     */
    public List<CourseProjection> selectProjection()
    {
        return courseRepository.findAllProjectedBy();
    }
    public CourseDto selectById(Long id) {
        Course course=courseRepository.findById(id).orElseThrow(()->new NotFoundException("Course not found"));
        return convertor.entityToDto(course);
    }
    /**
     * Delete All course in database
     */
    public String deleteAll() {
        courseRepository.deleteAll();
        return "Delete All !!!";
    }

    /**
     * Delete specific course by id
     * @param id of course
     * @return information text
     */
    public String deleteById(Long id) {
        courseRepository.findById(id).orElseThrow(()->new DeleteException("courseId not found. No data to delete."));
        courseRepository.deleteById(id);
        return "Delete Course :"+ id;
    }

    /**
     * Update specific course by id
     * @param id course for update
     * @param obj Object for update
     * @return courseDto
     */
    public CourseDto update(Long id, CourseDto obj) {
        Course courseDb=courseRepository.findById(id).get();
        if(obj.getStudentId()!=null)
        {
            Student student=studentRepository.findById(obj.getStudentId()).get();
            courseDb.addStudent(student);
        }
        if(obj.getCredit()!=null)
        courseDb.setCredit(obj.getCredit());
        if(obj.getTitle()!=null)
        courseDb.setTitle(obj.getTitle());
        if(obj.getTeacherId()!=null)
        courseDb.setTeacherId(obj.getTeacherId());
        courseRepository.save(courseDb);
        return convertor.entityToDto(courseDb);
    }
    /**
     * user for find specification filter with Pageable
     */
    public List<CourseDto> findSpecification(String filter, Pageable pageable)
    {
        List<Course> list= courseRepository.findAll(titleCourse(filter).or(creditCourse(filter)).or(idCourse(filter)),pageable).getContent();
        return convertor.entityToDto(list);
    }


}

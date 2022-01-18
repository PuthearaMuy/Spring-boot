package com.springboot.theara.service;

import com.springboot.theara.converter.CourseMaterialConvertor;
import com.springboot.theara.dto.CourseMaterialDto;
import com.springboot.theara.dto.projection.CourseMaterialProjection;
import com.springboot.theara.entity.Course;
import com.springboot.theara.entity.CourseMaterial;
import com.springboot.theara.exceptions.DeleteException;
import com.springboot.theara.exceptions.InvalidDataAccessException;
import com.springboot.theara.exceptions.NotFoundException;
import com.springboot.theara.repository.CourseMaterialRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.springboot.theara.service.specification.CourseMaterialSpecification.idCourseMaterial;
import static com.springboot.theara.service.specification.CourseMaterialSpecification.urlCourseMaterial;

@org.springframework.stereotype.Service
public class CourseMaterialService implements Service{
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;
    @Autowired
    private CourseService courseService;
    /**
     * User For convert CourseMaterial to CourseMaterialDto
     * And CourseMaterialDto to CourseMaterial entity
     */
    @Autowired
    private CourseMaterialConvertor convert;
    /**
     * Save CourseMaterial in to database by CourseMaterialDto
     * @param courseMaterialDto
     * @return CourseMaterialDto
     */
    public CourseMaterialDto save(CourseMaterialDto courseMaterialDto) {
        if(courseMaterialDto.getCourseDto().getStudentId()!=null||
        courseMaterialDto.getCourseDto().getTeacherId()!=null)
            throw new InvalidDataAccessException("teacherID or studentId not allow to save");
        CourseMaterial courseMaterial=courseMaterialRepository.save(convert.dtoToEntity(courseMaterialDto));
        return convert.entityToDto(courseMaterial);
    }
    /**
     * Get all CourseMaterial from database
     * @return CourseMaterialDto
     */
    @Override
    public List<CourseMaterialDto> selectAll() {
        List<CourseMaterial> list=courseMaterialRepository.findAll();
        return convert.entityToDto(list);
    }

    /**
     * Get all CourseMaterial projection
     * @return courseMaterialProjection
     */
    public List<CourseMaterialProjection> selectAllProjected()
    {
        return courseMaterialRepository.findAllProjectedBy();
    }

    /**
     * Get a specific courseMaterial by id
     * @param id of courseMaterial
     * @return CourseMaterialDto
     */
    public CourseMaterialDto selectById(Long id) {
        CourseMaterial course=courseMaterialRepository.findById(id).orElseThrow(()->new NotFoundException("Course-Material not found."));
        return convert.entityToDto(course);
    }

    /**
     * Delete all courseMaterial
     */
    @Override
    public String deleteAll() {
        courseMaterialRepository.deleteAll();
        return "Delete All !!!";
    }
    /**
     * delete specific courseMaterial By courseMaterialId
     */
    @Override
    public String deleteById(Long id) {
        courseMaterialRepository.findById(id).orElseThrow(()->new DeleteException("courseMaterialId not found. No value to delete."));
        courseMaterialRepository.deleteById(id);
        return "Delete Course :"+ id;
    }
    /**
     * Update specific CourseMaterial by id
     * @param id CourseMaterialId
     * @param obj CourseMaterialDto
     * @return courseMaterialDto
     */
    public CourseMaterialDto update(Long id, CourseMaterialDto obj) {
        CourseMaterial courseMaterialDb=courseMaterialRepository.findById(id).get();
        courseMaterialDb.setUrl(obj.getUrl());
        Long oldCourseId=courseMaterialDb.getCourse().getCourseId();
        ModelMapper map=new ModelMapper();
        if(obj.getCourseDto()!=null)
        courseMaterialDb.setCourse(map.map(obj.getCourseDto(),Course.class));
        courseMaterialRepository.save(courseMaterialDb);
        String delete=courseService.deleteById(oldCourseId);
        System.out.println(delete);
        return obj;
    }
    /**
     * Get CourseMaterial by Pageable and Specification filter
     * @param filter specification(courseMaterialId,url) allow null
     * @param page Pageable allow null
     * @return List courseMaterial
     */
    public List<CourseMaterialDto> findByPageableWithFilter(String filter,Pageable page)
    {
        List<CourseMaterial> list=courseMaterialRepository.findAll(urlCourseMaterial(filter).or(idCourseMaterial(filter)),page).getContent();
        return convert.entityToDto(list);
    }

}

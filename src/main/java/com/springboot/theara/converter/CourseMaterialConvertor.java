package com.springboot.theara.converter;

import com.springboot.theara.dto.CourseDto;
import com.springboot.theara.dto.CourseMaterialDto;
import com.springboot.theara.entity.CourseMaterial;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMaterialConvertor {

	@Autowired
	private ModelMapper map;

	public CourseMaterialDto entityToDto(CourseMaterial courseMaterial)
	{
//		var material =  map.map(courseMaterial, CourseMaterial.class);
//		var cos = map.map(courseMaterial.getCourse(), Course.class);
//		material.setCourse(cos);

		CourseMaterialDto dto=map.map(courseMaterial,CourseMaterialDto.class);
		CourseDto courseDto=map.map(courseMaterial.getCourse(),CourseDto.class);
		dto.setCourseDto(courseDto);
		return dto;
	}
	
	public CourseMaterial dtoToEntity(CourseMaterialDto courseMaterialDto)
	{
		CourseMaterial courseMaterial=map.map(courseMaterialDto,CourseMaterial.class);
		return courseMaterial;	
	}
	
	public List<CourseMaterialDto> entityToDto(List<CourseMaterial> courseMaterial)
	{
		return courseMaterial.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
	}
	public List<CourseMaterial> dtoToEntity(List<CourseMaterialDto> courseMaterialdto)
	{
		return courseMaterialdto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
	}


}

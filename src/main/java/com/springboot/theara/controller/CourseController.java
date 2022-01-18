package com.springboot.theara.controller;

import com.springboot.theara.dto.CourseDto;
import com.springboot.theara.dto.projection.CourseProjection;
import com.springboot.theara.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/course")
@Tag(name = "Course", description = "Endpoints for managing Course.")
@SecurityRequirement(name = "Endpoint Security")
public class CourseController {
    @Autowired
    private CourseService courseService;


    /**
     * save only course information
     */
    @Operation(summary = "Save Course", description = "Save only course information in to database. Do not input studentId we save only course information.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save course success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = " courseId is auto generate value")
    @PostMapping()
    public CourseDto save(@RequestBody CourseDto dto) {
        log.info("dto: {}", dto);
        return courseService.save(dto);
    }

    /**
     * save course with student id because course is many-to-many relationship
     */
    @Operation(summary = "Save Course with StudentID", description = "Save course with StudentId to make Many-To-Many Relationship")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save course success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "studentId is not null")
    @PostMapping("/student")
    public CourseDto saveWithStudent(@RequestBody CourseDto dto) {
        System.out.println(dto);
        return courseService.saveWithStudent(dto);
    }

    /**
     * Get data from course table
     */
    @Operation(summary = "Get Course Projection", description = "This method we use for select course projection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found course",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseProjection.class))}),
            @ApiResponse(responseCode = "404", description = "Not found this course", content = @Content)})
    @GetMapping("/projection")
    public List<CourseProjection> fetchByProject() {
        return courseService.selectProjection();
    }

    /**
     * Get all Data form Course table
     */
    @Operation(summary = "Select Course", description = "Select all course return List course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get course success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @GetMapping()
    public List<CourseDto> selectAll() {
        return courseService.selectAll();
    }

    /**
     * Get a specific course by id
     */
    @Operation(summary = "Select Course By Id", description = "Select specification course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find course success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @Parameter(name = "CourseId", example = "1")
    @GetMapping("/{CourseId}")
    public CourseDto selectById(@PathVariable("CourseId") Long id) {
        return courseService.selectById(id);
    }

    /**
     * Delete All course
     */
    @Operation(summary = "Delete All Course", description = "Delete all course in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete all course success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @Deprecated
    @DeleteMapping()
    public String delete() {
        return courseService.deleteAll();
    }

    /**
     * Delete a specific course by CourseId
     */
    @Operation(summary = "Delete a specific Course", description = "Delete a specific course by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete course success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @Parameter(name = "CourseId", example = "1")
    @DeleteMapping("/{CourseId}")
    public String deleteById(@PathVariable("CourseId") Long id) {
        return courseService.deleteById(id);
    }

    /**
     * Update on specific course by id
     */
    @Operation(summary = "Update Course", description = "Update course return course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update course success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @Parameter(name = "CourseId", example = "1")
    @PutMapping("/{CourseId}")
    public CourseDto update(@PathVariable("CourseId") Long id, @RequestBody CourseDto courseDto) {
        return courseService.update(id, courseDto);
    }

    /**
     * Get Data from Course By Pagination and Specification filter
     */
    @Operation(summary = "Pageable Course and Specification Filter", description = "Select course return Pageable course")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagination course success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @Parameters(value = {
            @Parameter(name = "page", example = "{\n" +
                    "  \"page\": 1,\n" +
                    "  \"size\": 5\n" +
                    "}"),
            @Parameter(name = "filter", description = "Filter By: courseId,title,credit", example = "english")
    })
    @GetMapping("/pageable")
    public ResponseEntity<List<CourseDto>> findByPageable(@PageableDefault(page = 1, size = 5) Pageable page, @RequestParam(defaultValue = "") String filter) {
        if (page.getPageNumber() == 0)
            page = PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by("courseId"));
        else
            page = PageRequest.of(page.getPageNumber() - 1, page.getPageSize(), Sort.by("courseId"));
        return ResponseEntity.status(HttpStatus.OK)
                .body(courseService.findSpecification(filter, page));
    }
}

package com.springboot.theara.controller;

import com.springboot.theara.dto.StudentDto;
import com.springboot.theara.dto.projection.StudentProjection;
import com.springboot.theara.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/v1/student")
@Tag(name = "Student",description = "Endpoints for managing student")
@SecurityRequirement(name = "Endpoint Security")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * Sava Student information controller
     */
    @Operation(summary = "Sava Student",description = "Sava Student information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Save Student Success",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)
    })
    @PostMapping()
    public StudentDto save(@RequestBody StudentDto dto)
    {
        return studentService.save(dto);
    }

    /**
     * Get all StudentProjection
     */
    @Operation(summary = "Get All Student",description = "Get all student projection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Student Projection Success",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StudentProjection.class))),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)
    })
    @GetMapping("/projection")
    public List<StudentProjection> selectAllProjection()
    {
        return studentService.selectAllProjection();
    }

    /**
     * Get all Student from database
     */
    @Operation(summary = "Get All Student",description = "Get all student from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Student Success",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)
    })
    @GetMapping()
    public List<StudentDto> findAll()
    {
        return studentService.selectAll();
    }

    /**
     * Get Specific Student By studentId
     */
    @Operation(summary = "Get Specific Student",description = "Get specific student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Student Success",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content),
            @ApiResponse(responseCode = "500",description = "Not Found",content = @Content)
    })
    @Parameter(name = "StudentId",example = "1")
    @GetMapping("/{StudentId}")
    public StudentDto findById(@PathVariable("StudentId")Long id)
    {
        return studentService.selectById(id);
    }

    /**
     * Update Specific Student by StudentId
     */
    @Operation(summary = "Update Specific Student",description = "Update specific student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Update Student Success",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)
    })
    @Parameter(name = "StudentId",example = "1")
    @PutMapping("/{StudentId}")
    public StudentDto update(@PathVariable("StudentId")Long id,@RequestBody StudentDto dto)
    {
        return studentService.update(id,dto);
    }

    /**
     * Delete Specific Student by StudentId
     */
    @Operation(summary = "Delete Specific Student",description = "Delete specific student by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Delete Student Success",content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)
    })
    @Parameter(name = "StudentId",example = "1")
    @DeleteMapping("/{StudentId}")
    public String delete(@PathVariable("StudentId")Long id)
    {
        return studentService.deleteById(id);
    }

    /**
     * Get Student By Pagination and Specification filter
     */
    @Operation(summary = "Get Pagination Student By Specification Filter",description = "Get student return student pageable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get Student Pagination Success",content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = StudentDto.class))),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)
    })
    @Parameters(value = {
            @Parameter(name = "page",example = "{\n" +
                    "  \"page\": 1,\n" +
                    "  \"size\": 5\n" +
                    "}"),
            @Parameter(name = "filter",description = "filter by StudentId,fistName,lastName,emailId")
    })
    @GetMapping("/pageable")
    public List<StudentDto> findByPageable(@PageableDefault(page = 1,size = 5)Pageable page, @RequestParam(defaultValue = "")String filter)
    {
        page= PageRequest.of(page.getPageNumber()-1,page.getPageSize(),Sort.by("studentId"));
        return studentService.findByPageable(filter,page);
    }
}

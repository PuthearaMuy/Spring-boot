package com.springboot.theara.controller;

import com.springboot.theara.dto.TeacherDto;
import com.springboot.theara.dto.projection.TeacherProjection;
import com.springboot.theara.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("v1/teacher")
@Tag(name = "Teacher",description = "Endpoint for managing teacher")
@SecurityRequirement(name = "Endpoint Security")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * Save Teacher in to database
     */
    @Operation(summary = "Save Teacher",description = "Save teacher in to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Save success",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TeacherDto.class))),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)
    })
    @PostMapping()
    public TeacherDto save(@RequestBody TeacherDto teacher)
    {
        System.out.println(teacher);
        return teacherService.save(teacher);
    }

    /**
     * Get All Teacher Projection
     */
    @Operation(summary = "Get All Teacher",description = "Get all teacher projection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get success",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TeacherProjection.class))),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)
    })
    @GetMapping("/projection")
    public List<TeacherProjection> fetchByProjection()
    {
        return teacherService.selectByProjection();
    }

    /**
     * Get All Teacher from database
     */
    @Operation(summary = "Get All Teacher",description = "Get all teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get success",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TeacherDto.class))),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)
    })
    @GetMapping()
    public List<TeacherDto> selectAll()
    {
        return teacherService.selectAll();
    }

    /**
     * Get specific teacher by teacherId
     */
    @Operation(summary = "Get A Specific Teacher",description = "Get a specific teacher by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get success",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TeacherDto.class))),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)
    })
    @Parameter(name = "id",example = "1")
    @GetMapping("/{id}")
    public TeacherDto findById(@PathVariable long id)
    {
        return teacherService.selectById(id);
    }

    /**
     * Delete all teacher from database
     */
    @Operation(summary = "Delete Teacher",description = "Delete all teacher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Delete success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)
    })
    @DeleteMapping()
    @Deprecated
    public String deleteAll()
    {
        teacherService.deleteAll();
        return "Delete All Teacher Field";
    }
    /**
     * Delete specific Teacher by TeacherId
     */
    @Operation(summary = "Delete A Specific Teacher",description = "Delete a specific teacher by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Delete success",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)
    })
    @Parameter(name = "id",example = "1")
    @DeleteMapping("/{id}")
    public String deletebyId(@PathVariable("id")Long id)
    {
        teacherService.deleteById(id);
        return "Delete Teacher:"+id;
    }

    /**
     * Update specific teacher by teacherId
     */
    @Operation(summary = "Update Teacher",description = "Update teacher by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Update success",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TeacherDto.class))),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)
    })
    @Parameter(name = "id",example = "1")
    @PutMapping("/{id}")
    public TeacherDto update(@PathVariable("id")Long id,@RequestBody TeacherDto teacher)
    {
        return teacherService.update(id,teacher);
    }

    /**
     * Get Teacher by Pagination and Specification filter
     */
    @Operation(summary = "Get Pagination Teacher and Specification Filter",description = "Get pagination teacher by specification filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get success",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = TeacherDto.class))),
            @ApiResponse(responseCode = "400",description = "Bad request",content = @Content)
    })
    @Parameter(name="page",example = "{\n" +
            "  \"page\": 1,\n" +
            "  \"size\": 5\n" +
            "}")
    @Parameter(name = "filter",description = "filter by firstName,lastName,Id")
    @GetMapping("/pageable")
    public List<TeacherDto> findByPageable(@PageableDefault(page=1,size=5)Pageable page, @RequestParam(defaultValue = "")String filter)
    {
        page= PageRequest.of(page.getPageNumber()-1,page.getPageSize(),Sort.by("teacherId"));
        return teacherService.findByPageable(filter,page);
    }
}

package com.springboot.theara.controller;
import com.springboot.theara.dto.CourseMaterialDto;
import com.springboot.theara.dto.projection.CourseMaterialProjection;
import com.springboot.theara.service.CourseMaterialService;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/v1/course-material")
@Tag(name = "Course-Material",description = "Endpoints for managing Course Material")
@SecurityRequirement(name = "Endpoint Security")
public class CourseMaterialController {
    @Autowired
    private CourseMaterialService courseMaterialService;

    /**
     * Save course-material with course because OneToOne Relationship
     */
    @Operation(summary = "Save Course Material",description = "Save course material with course to make One-To-One relationship and StudentId can not save")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Save CourseMaterial success",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = CourseMaterialDto.class))}),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)})
    @PostMapping()
    public CourseMaterialDto save(@RequestBody CourseMaterialDto courseMaterialdto)
    {
        return courseMaterialService.save(courseMaterialdto);
    }

    /**
     * Get Only courseMaterial Projection
     */
    @Operation(summary = "Select Course Material",description = "Select all course material return List course projection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get CourseMaterial Projection success",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = CourseMaterialProjection.class))}),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)})
    @GetMapping("/projection")
    public List<CourseMaterialProjection> selectAllProjected()
    {
        return courseMaterialService.selectAllProjected();
    }

    /**
     * Get all data of courseMaterial
     */
    @Operation(summary = "Select Course Material",description = "Select all course return List course material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get CourseMaterial success",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = CourseMaterialDto.class))}),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)})
    @GetMapping()
    public List<CourseMaterialDto> selectAll()
    {
        return courseMaterialService.selectAll();
    }

    /**
     * Get specific courseMaterial by id
     */
    @Operation(summary = "Select A Specific Course Material",description = "Select a specific Course Material By ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get CourseMaterial success",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = CourseMaterialDto.class))}),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)})
    @Parameter(name = "id", example = "1")
    @GetMapping("/{id}")
    public CourseMaterialDto selectById(@PathVariable("id")Long id)
    {
         return courseMaterialService.selectById(id);
    }
    /**
     * Delete all courseMaterial data
     */
    @Operation(summary = "Delete Course Material",description = "Delete all course material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Delete CourseMaterial success",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",description = "Bad Request")})
    @Deprecated
    @DeleteMapping()
    public String delete()
    {
        return courseMaterialService.deleteAll();
    }

    /**
     * Delete specific courseMaterial by id
     */
    @Operation(summary = "Delete A Course Material",description = "Delete a specific course material by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Delete CourseMaterial By Id success",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",description = "Bad Request")})
    @Parameter(name = "id",example = "1")
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id")Long id)
    {
        return courseMaterialService.deleteById(id);
    }

    /**
     * update specific courseMaterial by id
     */
    @Operation(summary = "Update Course Material",description = "Update a course material by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Update CourseMaterial success",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = CourseMaterialDto.class))}),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)})
    @Parameter(name = "id",example = "1")
    @PutMapping("/{id}")
    public CourseMaterialDto update(@PathVariable("id") Long id,@RequestBody CourseMaterialDto courseMaterialdto)
    {
        return courseMaterialService.update(id,courseMaterialdto);
    }

    /**
     * Get data by pagination and specification filter
     */
    @Operation(summary = "Pagination Course Material with Specification Filter",description = "Select all course material return pageable of course material")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Get CourseMaterial Pagination success",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = CourseMaterialDto.class))}),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = @Content)})
    @Parameter(name = "page",example = "{\n" +
            "  \"page\": 1,\n" +
            "  \"size\": 5\n" +
            "}")
    @GetMapping("/pageable")
    public List<CourseMaterialDto> findByUrlPageable(@PageableDefault(page=1,size = 5)Pageable page, @RequestParam(defaultValue = "") String filter)
    {
        if(page.getPageNumber()==0)
            page= PageRequest.of(page.getPageNumber(),page.getPageSize());
        else
            page= PageRequest.of(page.getPageNumber()-1,page.getPageSize());
        return courseMaterialService.findByPageableWithFilter(filter,page);
    }
}

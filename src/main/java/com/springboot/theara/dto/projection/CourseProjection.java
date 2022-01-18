package com.springboot.theara.dto.projection;


import io.swagger.v3.oas.annotations.media.Schema;

public interface CourseProjection {
    @Schema(
            name = "title",description = "Course Title",type = "String",example = "khmer history"
    )
    String getTitle();
    @Schema(
            name="credit",description = "Course Credit",type = "Integer",example = "5"
    )
    Integer getCredit();
}

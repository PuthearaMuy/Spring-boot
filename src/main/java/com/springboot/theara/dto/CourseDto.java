package com.springboot.theara.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseDto {
    @Schema(
            name="courseId", description = "AutoGenerate",type = "Long",example = "1",readOnly = true
    )
    private Long courseId;
    @Schema(
            name = "title",description = "Course Title",type = "String",example = "khmer history"
    )
    private String title;
    @Schema(
            name="credit",description = "Course Credit",type = "Integer",example = "5"
    )
    private Integer credit;
    @Schema(
            name = "teacherId",description = "Many-To-One Relationship",type = "Long",example = "1"
    )
    private Long teacherId;
    @Schema(
            name = "studentId",description = "Many-To-Many Relationship",type = "Long",example = "1"
    )
    private Long studentId;
}

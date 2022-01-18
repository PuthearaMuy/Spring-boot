package com.springboot.theara.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherDto {
    @Schema(
            name = "teacherId",description = "AutoGenerate Number",type = "Long",example = "1",readOnly = true
    )
    private Long teacherId;
    @Schema(
            name = "firstName",description = "FirstName of teacher",type = "String",example = "Sokea"
    )
    private String firstName;
    @Schema(
            name = "lastName",description = "LastName of teacher",type = "String",example = "Thia"
    )
    private String lastName;
    private List<CourseDto> course;

}

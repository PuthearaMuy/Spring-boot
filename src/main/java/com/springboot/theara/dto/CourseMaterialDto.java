package com.springboot.theara.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseMaterialDto {
    @Schema(
            name = "courseMaterialId",description = "AutoGenerate",type = "Long",example = "1",readOnly = true
    )
	private Long courseMaterialId;
    @Schema(
            name = "url",description = "Url of course",type = "String",example = "www.google.com"
    )
    private String url;
    private CourseDto courseDto;

}

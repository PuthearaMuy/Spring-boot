package com.springboot.theara.dto.projection;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Value;

public interface TeacherProjection {
    @Schema(
            name = "teacherId",description = "AutoGenerate Number",type = "Long",example = "1"
    )
    Long getTeacherId();
    @Schema(
            name = "fullName",description = "FullName of teacher",type = "String",example = "Sokea Thia"
    )
    @Value("#{target.firstName+' '+target.lastName}")
    String getFullName();
}

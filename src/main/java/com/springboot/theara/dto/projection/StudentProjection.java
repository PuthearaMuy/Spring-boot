package com.springboot.theara.dto.projection;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Value;

public interface StudentProjection {
    @Schema(
            name = "emailId",description = "Email of student",type = "String",example = "studentname@gmail.com"
    )
    String getEmailId();
    @Schema(
            name = "fullName",description = "FullName Student",type = "String",example = "Long kakda"
    )
    @Value("#{target.firstName+' '+target.lastName}")
    String getFullName();
}

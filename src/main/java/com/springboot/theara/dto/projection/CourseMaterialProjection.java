package com.springboot.theara.dto.projection;

import io.swagger.v3.oas.annotations.media.Schema;

public interface CourseMaterialProjection {
    @Schema(
            name = "url",description = "Url of course",type = "String",example = "www.google.com"
    )
    String getUrl();
    @Schema(
            name = "courseMaterialId",description = "AutoGenerate",type = "Long",example = "1"
    )
    Long getCourseMaterialId();
}

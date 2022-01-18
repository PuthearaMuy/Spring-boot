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
public class StudentDto {
    @Schema(
            name = "studentId",description = "AutoGenerate Number",type = "Long",example = "1",readOnly = true
    )
    private long studentId;
    @Schema(
            name = "firstName",description = "FirstName Student",type = "String",example = "Dara"
    )
    private String firstName;
    @Schema(
            name = "lastName",description = "LastName Student",type = "String",example = "Long"
    )
    private String lastName;
    @Schema(
            name = "emailId",description = "Email of student \n Not duplicate",type = "String",example = "studentname@gmail.com"
    )
    private String emailId;
    @Schema(
            name = "guardianName",description = "Guardian Name",type = "String",example = "song sochea"
    )
    private String guardianName;
    @Schema(
            name = "guardianEmail",description = "GuardianEmail",type = "String",example = "songsochea@gmail.com"
    )
    private String guardianEmail;
    @Schema(
            name = "guardianMobile",description = "Mobile Number",type = "String",example = "012 365 412"
    )
    private String guardianMobile;



}

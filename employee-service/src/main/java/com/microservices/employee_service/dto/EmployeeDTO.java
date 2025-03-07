package com.microservices.employee_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Employee Model Information"
)
public class EmployeeDTO {

    private  Long id;
    @Schema(
            description = "Employee Model firstName"
    )
    private String firstName;
    @Schema(
            description = "Employee Model LastName"
    )
    private String lastName;
    @Schema(
            description = "Employee Model email"
    )
    private String email;
    @Schema(
            description = "Employee's Department code"
    )
    private String departmentCode;
    @Schema(
            description = "Employee's Organization Code"
    )
    private String organizationCode;
}
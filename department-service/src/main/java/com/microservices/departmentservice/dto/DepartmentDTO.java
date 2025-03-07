package com.microservices.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "Department DTO model Information"
)
public class DepartmentDTO {

    private Long id;
    @Schema(
            description = "Department Name"
    )
    private String departmentName;
    @Schema(
            description = "Department Description"
    )
    private String departmentDescription;
    @Schema(
            description = "Department Code"
    )
    private String departmentCode;
}
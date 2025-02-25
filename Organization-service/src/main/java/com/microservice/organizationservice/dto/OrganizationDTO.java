package com.microservice.organizationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrganizationDTO {

    private Long id;
    private String organizationName;
    private String organizationDescription;
    private String organizationCode;
    private String createdDate;
}
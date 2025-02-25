package com.microservice.organizationservice.mapper;

import com.microservice.organizationservice.dto.OrganizationDTO;
import com.microservice.organizationservice.entity.Organization;

public class OrganizationMapper {

//    convert entity to DTO
    public static OrganizationDTO mapToOrganizationDTO(Organization organization) {
        OrganizationDTO organizationDTO = new OrganizationDTO(
                organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getCreatedDate()
        );
        return organizationDTO;
    }

//    Convert DTO to Entity
    public static Organization mapToOrganization(OrganizationDTO organizationDTO) {
        Organization organization = new Organization(
                organizationDTO.getId(),
                organizationDTO.getOrganizationName(),
                organizationDTO.getOrganizationDescription(),
                organizationDTO.getOrganizationCode(),
                organizationDTO.getCreatedDate()
        );
        return organization;
    }

}
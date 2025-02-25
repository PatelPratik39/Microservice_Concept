package com.microservice.organizationservice.service;

import com.microservice.organizationservice.dto.OrganizationDTO;

public interface OrganizationService {

    OrganizationDTO saveOrganization(OrganizationDTO organizationDTO);

    OrganizationDTO findOrganizationByCode(String organizationCode);
}
package com.microservice.organizationservice.service.impl;

import com.microservice.organizationservice.controller.OrganizationController;
import com.microservice.organizationservice.dto.OrganizationDTO;
import com.microservice.organizationservice.entity.Organization;
import com.microservice.organizationservice.mapper.OrganizationMapper;
import com.microservice.organizationservice.repository.OrganizationRepository;
import com.microservice.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDTO saveOrganization(OrganizationDTO organizationDTO) {

        Organization organization = OrganizationMapper.mapToOrganization(organizationDTO);   //convert dto to JPA entity
        Organization savedOrganization =  organizationRepository.save(organization);
        OrganizationDTO savedOrganizationDTO = OrganizationMapper.mapToOrganizationDTO(savedOrganization); // convert Jpa entity to dto
        LOGGER.info("Received request to create organization");
        return savedOrganizationDTO;
    }

    @Override
    public OrganizationDTO findOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDTO(organization);
    }
}
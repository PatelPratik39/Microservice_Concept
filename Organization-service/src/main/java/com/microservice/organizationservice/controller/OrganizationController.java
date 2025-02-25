package com.microservice.organizationservice.controller;

import com.microservice.organizationservice.dto.OrganizationDTO;
import com.microservice.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organization")
@AllArgsConstructor
public class OrganizationController {

    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationDTO organizationDTO) {
        OrganizationDTO savedOrganization =  organizationService.saveOrganization(organizationDTO);
        return new ResponseEntity<>(savedOrganization, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<OrganizationDTO> getOrganizationByCode(@PathVariable("code") String organizationCode) {
        OrganizationDTO organizationDTO = organizationService.findOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDTO);
    }
}
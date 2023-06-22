package com.qunapaq.zenquna.controller;

import com.qunapaq.zenquna.model.Organization;
import com.qunapaq.zenquna.exception.ValidationException;
import com.qunapaq.zenquna.model.User;
import com.qunapaq.zenquna.repository.OrganizationRepository;
import com.qunapaq.zenquna.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zq/v1")
@CrossOrigin(origins = "*")
public class OrganizationController {
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;

    public OrganizationController(OrganizationRepository organizationRepository,
                                  UserRepository userRepository)
    {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
    }

    //EndPoint: http://localhost:8080/api/zq/v1/organizations
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/organizations")
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        return new ResponseEntity<List<Organization>>(organizationRepository.findAll(), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/organizations
    //Method: POST
    @Transactional
    @PostMapping("/organizations")
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        User user = userRepository.findById(organization.getId())
                .orElseThrow(() -> new ValidationException("User not found"));
        organization.setUser(user);
        existsByRuc(organization);
        validationOrganization(organization);
        return new ResponseEntity<>(organizationRepository.save(organization), HttpStatus.CREATED);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/organizations
    //Method: PUT
    @Transactional
    @PutMapping("/organizations")
    public ResponseEntity<Organization> updateOrganization(@RequestBody Organization organization) {
        existsByRuc(organization);
        validationOrganization(organization);
        return new ResponseEntity<>(organizationRepository.save(organization), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/organizations
    //Method: DELETE
    @Transactional
    @DeleteMapping("/organizations")
    public ResponseEntity<Void> deleteOrganization(@RequestBody Organization organization) {
        organizationRepository.delete(organization);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void existsByRuc(Organization organization) {
        if (organization.getRUC() != null && organizationRepository.existsByRUC(organization.getRUC())) {
            throw new ValidationException("RUC already exists");
        }
    }

    public void validationOrganization(Organization organization) {
        if(organization.getRUC() != null && organization.getRUC().length() != 11){
            throw new ValidationException("El RUC debe tener 11 dígitos");
        }
        if (organization.getName() == null || organization.getName().isEmpty()) {
            throw new ValidationException("El nombre es obligatorio");
        }
        if(organization.getDescription()==null || organization.getDescription().isEmpty()){
            throw new ValidationException("Se necesita una descripción");
        }
    }
}

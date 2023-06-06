package com.qunapaq.zenquna.controller;

import com.qunapaq.zenquna.model.Campaign;
import com.qunapaq.zenquna.exception.ValidationException;
import com.qunapaq.zenquna.model.Location;
import com.qunapaq.zenquna.model.Organization;
import com.qunapaq.zenquna.repository.CampaignRepository;
import com.qunapaq.zenquna.repository.OrganizationRepository;
/*import com.qunapaq.zenquna.repository.UserRepository;*/
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/zq/v1")
public class CampaignController {
    private final CampaignRepository campaignRepository;
    private final OrganizationRepository organizationRepository;
    /*private final UserRepository userRepository;*/

    public CampaignController(CampaignRepository campaignRepository,
                              OrganizationRepository organizationRepository
                              /*,UserRepository userRepository*/)
    {
        this.campaignRepository = campaignRepository;
        this.organizationRepository = organizationRepository;
        /*this.userRepository = userRepository;*/
    }

    //EndPoint: http://localhost:8080/api/zq/v1/campaigns
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/campaigns")
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        return new ResponseEntity<List<Campaign>>(campaignRepository.findAll(), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/campaigns
    //Method: POST
    @Transactional
    @PostMapping("/campaigns")
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        Organization organization = organizationRepository.findById(campaign.getOrganization().getId())
                .orElseThrow(() -> new ValidationException("Organization not found"));

        // Asignar la organización a la campaña
        campaign.setOrganization(organization);

        // Asignar la campaña a cada ubicación
        if (campaign.getLocations() != null) {
            for (Location location : campaign.getLocations()) {
                location.setCampaign(campaign);
            }
        }

        existsByOrganizationId(campaign);
        validationCampaign(campaign);
        return new ResponseEntity<>(campaignRepository.save(campaign), HttpStatus.CREATED);
    }


    //EndPoint: http://localhost:8080/api/zq/v1/campaigns
    //Method: PUT
    @Transactional
    @PutMapping("/campaigns")
    public ResponseEntity<Campaign> updateCampaign(@RequestBody Campaign campaign) {
        existsByOrganizationId(campaign);
        validationCampaign(campaign);
        return new ResponseEntity<>(campaignRepository.save(campaign), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/campaigns
    //Method: DELETE
    @Transactional
    @DeleteMapping("/campaigns")
    public ResponseEntity<Campaign> deleteCampaign(@RequestBody Campaign campaign) {
        campaignRepository.delete(campaign);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void existsByOrganizationId(Campaign campaign) {
        if (!organizationRepository.existsById(campaign.getOrganization().getId())) {
            throw new ValidationException("Organization not found");
        }
    }

    private void validationCampaign(Campaign campaign) {
        if (campaign.getName() == null || campaign.getName().isEmpty()) {
            throw new ValidationException("Name is required");
        }
        if (campaign.getDescription() == null || campaign.getDescription().isEmpty()) {
            throw new ValidationException("Description is required");
        }
        if (campaign.getGoal() == 0) {
            throw new ValidationException("Goal is required");
        }
        if (campaign.getStartDate() == null) {
            throw new ValidationException("StartDate is required");
        }
        if (campaign.getEndDate() == null) {
            throw new ValidationException("EndDate is required");
        }
        if (campaign.getStartDate().isAfter(campaign.getEndDate())) {
            throw new ValidationException("StartDate must be before EndDate");
        }
        if (campaign.getOrganization() == null) {
            throw new ValidationException("Organization is required");
        }
        if (campaign.getOrganization().getId() == null) {
            throw new ValidationException("OrganizationId is required");
        }
        if (campaign.getOrganization().getId() == 0) {
            throw new ValidationException("OrganizationId is required");
        }
        if (!organizationRepository.existsById(campaign.getOrganization().getId())) {
            throw new ValidationException("Organization not found");
        }
        if (campaign.getStatus() == null || campaign.getStatus().isEmpty()) {
            throw new ValidationException("Status is required");
        }
        if (!campaign.getStatus().equals("ACTIVE") && !campaign.getStatus().equals("INACTIVE")) {
            throw new ValidationException("Status must be ACTIVE or INACTIVE");
        }
    }
}

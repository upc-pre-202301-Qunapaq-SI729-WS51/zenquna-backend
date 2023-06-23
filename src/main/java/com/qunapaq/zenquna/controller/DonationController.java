package com.qunapaq.zenquna.controller;

import com.qunapaq.zenquna.model.Donation;
import com.qunapaq.zenquna.repository.DonationRepository;
import com.qunapaq.zenquna.exception.ValidationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/zq/v1")
@CrossOrigin(origins = "*")
public class DonationController {

    private final DonationRepository donationRepository;


    public DonationController(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donor/{donorId}/donations
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/donor/{donorId}/donations")
    public ResponseEntity<Iterable<Donation>> getAllDonationsByDonorId(@PathVariable Long donorId) {
        return new ResponseEntity<Iterable<Donation>>(donationRepository.findAllByDonorId(donorId), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donation/{donationId}
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/donation/{donationId}")
    public ResponseEntity<Donation> getDonationById(@PathVariable Long donationId) {
        return new ResponseEntity<Donation>(donationRepository.findById(donationId).orElseThrow(() -> new ValidationException("Donation not found")), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donation
    //Method: POST
    @Transactional
    @PostMapping("/donation")
    public ResponseEntity<Donation> createDonation(@RequestBody Donation donation) {
        return new ResponseEntity<Donation>(donationRepository.save(donation), HttpStatus.CREATED);
    }
}

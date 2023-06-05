package com.qunapaq.zenquna.controller;

import com.qunapaq.zenquna.model.Donor;
import com.qunapaq.zenquna.exception.ValidationException;
import com.qunapaq.zenquna.repository.DonorRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zq/v1")
public class DonorController {
    private final DonorRepository donorRepository;

    public DonorController(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donors
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/donors")
    public ResponseEntity<List<Donor>> getAllDonors() {
        return new ResponseEntity<List<Donor>>(donorRepository.findAll(), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donors
    //Method: POST
    @Transactional
    @PostMapping("/donors")
    public ResponseEntity<Donor> createDonor(@RequestBody Donor donor) {
        existsByDni(donor);
        validationDonor(donor);
        return new ResponseEntity<>(donorRepository.save(donor), HttpStatus.CREATED);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donors
    //Method: PUT
    @Transactional
    @PutMapping("/donors")
    public ResponseEntity<Donor> updateDonor(@RequestBody Donor donor) {
        existsByDni(donor);
        validationDonor(donor);
        return new ResponseEntity<>(donorRepository.save(donor), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donors
    //Method: DELETE
    @Transactional
    @DeleteMapping("/donors")
    public ResponseEntity<Donor> deleteDonor(@RequestBody Donor donor) {
        existsByDni(donor);
        validationDonor(donor);
        return new ResponseEntity<>(donorRepository.save(donor), HttpStatus.OK);
    }

    //EndPoint: http://localhost:8080/api/zq/v1/donors/
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/donors/age/{age}")
    public ResponseEntity<List<Donor>> getDonorsByAge(@PathVariable int age) {
        return new ResponseEntity<List<Donor>>(donorRepository.findByAge(age), HttpStatus.OK);
    }

    /*filer by age range*/
    //EndPoint: http://localhost:8080/api/zq/v1/donors/
    //Method: GET
    @Transactional(readOnly = true)
    @RequestMapping("/donors/age/{minAge}/{maxAge}")
    public ResponseEntity<List<Donor>> getDonorsByAgeRange(@PathVariable int minAge, @PathVariable int maxAge) {
        return new ResponseEntity<List<Donor>>(donorRepository.findByAgeBetween(minAge, maxAge), HttpStatus.OK);
    }

    public void validationDonor(Donor donor) {
        if (donor.getDNI() == null || donor.getDNI().isEmpty()) {
            throw new ValidationException("El DNI es obligatorio");
        }
        if (donor.getDNI().length() > 8) {
            throw new ValidationException("El DNI no puede tener más de 8 caracteres");
        }
        if (donor.getFirstName() == null || donor.getFirstName().trim().isEmpty()) {
            throw new ValidationException("El nombre es obligatorio");
        }
        if (donor.getFirstName().length() > 22) {
            throw new ValidationException("El nombre no puede tener más de 22 caracteres");
        }
        if (donor.getLastName() == null || donor.getLastName().trim().isEmpty()) {
            throw new ValidationException("El apellido es obligatorio");
        }
        if (donor.getLastName().length() > 22) {
            throw new ValidationException("El apellido no puede tener más de 22 caracteres");
        }
        if(donor.getAge()==null){
            throw new ValidationException("La edad es obligatoria");
        }
    }


    public void existsByDni(Donor donor) {
        if (donorRepository.existsByDNI(donor.getDNI())) {
            throw new ValidationException("El DNI ya existe");
        }
    }

}

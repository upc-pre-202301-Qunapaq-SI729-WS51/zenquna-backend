package com.qunapaq.zenquna.controller;

import com.qunapaq.zenquna.model.Location;
import com.qunapaq.zenquna.exception.ValidationException;
import com.qunapaq.zenquna.repository.LocationRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zq/v1")
public class LocationController {
    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationRepository.findAll());
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(locationRepository.findById(id).orElseThrow(() -> new ValidationException("Location not found")));
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return new ResponseEntity<>(locationRepository.save(location), HttpStatus.CREATED);
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        Location locationToUpdate = locationRepository.findById(id).orElseThrow(() -> new ValidationException("Location not found"));
        locationToUpdate.setAddress(location.getAddress());
        locationToUpdate.setReference(location.getReference());
        locationToUpdate.setDistrict(location.getDistrict());
        locationToUpdate.setProvince(location.getProvince());
        locationToUpdate.setDepartment(location.getDepartment());
        return ResponseEntity.ok(locationRepository.save(locationToUpdate));
    }

    @DeleteMapping("/locations/{id}")
    @Transactional
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /*
    make the necessary for:

    List<Location> findByDistrict(String district);
    List<Location> findByProvince(String province);
    List<Location> findByDepartment(String department);
    * */

    @GetMapping("/locations/district/{district}")
    public ResponseEntity<List<Location>> getLocationByDistrict(@PathVariable String district) {
        return ResponseEntity.ok(locationRepository.findByDistrict(district));
    }

    @GetMapping("/locations/province/{province}")
    public ResponseEntity<List<Location>> getLocationByProvince(@PathVariable String province) {
        return ResponseEntity.ok(locationRepository.findByProvince(province));
    }

    @GetMapping("/locations/department/{department}")
    public ResponseEntity<List<Location>> getLocationByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(locationRepository.findByDepartment(department));
    }

}

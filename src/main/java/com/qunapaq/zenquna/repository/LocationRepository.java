package com.qunapaq.zenquna.repository;

import com.qunapaq.zenquna.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    /*List<Location> findByOrganizationName(String name);*/
    List<Location> findByDistrict(String district);
    List<Location> findByProvince(String province);
    List<Location> findByDepartment(String department);
}

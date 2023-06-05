package com.qunapaq.zenquna.repository;

import com.qunapaq.zenquna.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    List<Donor> findByAge(int age);
    List<Donor> findByAgeBetween(int minAge, int maxAge);
    boolean existsByDNI(String DNI);
}

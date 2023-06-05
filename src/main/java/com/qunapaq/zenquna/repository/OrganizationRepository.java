package com.qunapaq.zenquna.repository;

import com.qunapaq.zenquna.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    List<Organization> findByRUC(String RUC);
    boolean existsByRUC(String RUC);
}

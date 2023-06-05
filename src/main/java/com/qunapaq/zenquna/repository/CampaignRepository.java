package com.qunapaq.zenquna.repository;

import com.qunapaq.zenquna.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long>{
    List<Campaign> findByOrganizationName(String name);
    boolean existsByOrganizationId(String organizationId);
}

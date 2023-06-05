package com.qunapaq.zenquna.repository;

import com.qunapaq.zenquna.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{
    List<BankAccount> findByCampaignId(Long campaignId);
    boolean existsByCampaignId(Long campaignId);
}

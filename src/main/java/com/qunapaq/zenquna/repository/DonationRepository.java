package com.qunapaq.zenquna.repository;

import com.qunapaq.zenquna.model.Donation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT d FROM Donation d WHERE d.paymentData.donor.id = :donorId")
    List<Donation> findAllByDonorId(@Param("donorId") Long donorId);

    List<Donation> findByCampaignId(Long campaignId);

    List<Donation> findByDate(LocalDate date);

    List<Donation> findByDateBetween(LocalDate startDate, LocalDate endDate);
}



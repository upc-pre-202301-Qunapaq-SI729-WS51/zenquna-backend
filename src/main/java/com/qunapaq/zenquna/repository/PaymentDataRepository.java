package com.qunapaq.zenquna.repository;

import com.qunapaq.zenquna.model.PaymentData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PaymentDataRepository extends JpaRepository<PaymentData, Long>{
    List<PaymentData> findByDonorId(String donorId);
    boolean existsByDonorId(String donorId);
    @Query("SELECT CASE WHEN COUNT(pd) > 0 THEN 'true' ELSE 'false' END FROM PaymentData pd WHERE pd.expirationDate > CURRENT_DATE")
    boolean dateStillValid();
}

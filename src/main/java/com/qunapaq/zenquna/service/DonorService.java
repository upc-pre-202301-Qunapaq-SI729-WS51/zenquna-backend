package com.qunapaq.zenquna.service;

import com.qunapaq.zenquna.model.Donor;

public interface DonorService {
    Donor createDonor(Donor donor);
    Donor updateDonor(Donor donor);
    void deleteDonor(Long id);
    Donor getDonor(Long id);
}

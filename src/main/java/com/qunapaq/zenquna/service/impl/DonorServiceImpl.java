package com.qunapaq.zenquna.service.impl;

import com.qunapaq.zenquna.model.Donor;
import com.qunapaq.zenquna.repository.DonorRepository;
import com.qunapaq.zenquna.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorServiceImpl implements DonorService {
    @Autowired
    private DonorRepository donorRepository;

    @Override
    public Donor createDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    @Override
    public Donor updateDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    @Override
    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }

    @Override
    public Donor getDonor(Long id) {
        return donorRepository.findById(id).orElse(null);
    }
}

package com.enterprise.ent_invest_backend.Implementation;

import com.enterprise.ent_invest_backend.Model.Enterprise;
import com.enterprise.ent_invest_backend.Repository.EnterpriseRepository;
import com.enterprise.ent_invest_backend.Service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseServiceImplementation implements EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Override
    public Enterprise createEnterprise(Enterprise enterprise, MultipartFile imageFile) throws IOException {
        enterprise.setImageName(imageFile.getOriginalFilename());
        enterprise.setContentType(imageFile.getContentType());
        enterprise.setImageFile(imageFile.getBytes());
        return enterpriseRepository.save(enterprise);
    }

    @Override
    public Optional<Enterprise> getEnterpriseByEnterpriseId(String enterpriseId) {
        return enterpriseRepository.findById(enterpriseId);
    }

    @Override
    public List<Enterprise> getEnterpriseByUserId(String userId) {
        return enterpriseRepository.findByUserId(userId);
    }

    @Override
    public List<Enterprise> getEnterpriseByName(String enterpriseName) {
        return enterpriseRepository.findByEnterpriseName(enterpriseName);
    }

    @Override
    public List<Enterprise> getEnterpriseByCity(String city) {
        return enterpriseRepository.findByCity(city);
    }

    @Override
    public List<Enterprise> getAllEnterprises() {
        return enterpriseRepository.findAll();
    }

    @Override
    public Enterprise updateEnterprise(String enterpriseId, Enterprise enterpriseDetails) {
        return enterpriseRepository.findById(enterpriseId).map(existingEnterprise -> {
            if (enterpriseDetails.getUserId() != null && !enterpriseDetails.getUserId().isBlank()) {
                existingEnterprise.setUserId(enterpriseDetails.getUserId());
            }
            if (enterpriseDetails.getEnterpriseName() != null && !enterpriseDetails.getEnterpriseName().isBlank()) {
                existingEnterprise.setEnterpriseName(enterpriseDetails.getEnterpriseName());
            }
            if (enterpriseDetails.getEnterpriseEmail() != null && !enterpriseDetails.getEnterpriseEmail().isBlank()) {
                existingEnterprise.setEnterpriseEmail(enterpriseDetails.getEnterpriseEmail());
            }
            if (enterpriseDetails.getRegisterNumber() != null && !enterpriseDetails.getRegisterNumber().isBlank()) {
                existingEnterprise.setRegisterNumber(enterpriseDetails.getRegisterNumber());
            }
            if (enterpriseDetails.getEnterpriseType() != null && !enterpriseDetails.getEnterpriseType().isBlank()) {
                existingEnterprise.setEnterpriseType(enterpriseDetails.getEnterpriseType());
            }
            if (enterpriseDetails.getStartingDate() != null && !enterpriseDetails.getStartingDate().isBlank()) {
                existingEnterprise.setStartingDate(enterpriseDetails.getStartingDate());
            }
            if (enterpriseDetails.getAddress() != null && !enterpriseDetails.getAddress().isBlank()) {
                existingEnterprise.setAddress(enterpriseDetails.getAddress());
            }
            if (enterpriseDetails.getCity() != null && !enterpriseDetails.getCity().isBlank()) {
                existingEnterprise.setCity(enterpriseDetails.getCity());
            }
            if (enterpriseDetails.getTelNumber() != null && !enterpriseDetails.getTelNumber().isBlank()) {
                existingEnterprise.setTelNumber(enterpriseDetails.getTelNumber());
            }
            if (enterpriseDetails.getWebUrl() != null && !enterpriseDetails.getWebUrl().isBlank()) {
                existingEnterprise.setWebUrl(enterpriseDetails.getWebUrl());
            }
            if (enterpriseDetails.getImageName() != null && !enterpriseDetails.getImageName().isBlank()) {
                existingEnterprise.setImageName(enterpriseDetails.getImageName());
            }
            if (enterpriseDetails.getImageFile() != null && enterpriseDetails.getImageFile().length > 0) {
                existingEnterprise.setImageFile(enterpriseDetails.getImageFile());
            }
            if (enterpriseDetails.getContentType() != null && !enterpriseDetails.getContentType().isBlank()) {
                existingEnterprise.setContentType(enterpriseDetails.getContentType());
            }

            return enterpriseRepository.save(existingEnterprise);
        }).orElseThrow(() -> new RuntimeException("Enterprise not found with ID: " + enterpriseId));
    }


    @Override
    public void deleteEnterprise(String enterpriseId) {
        enterpriseRepository.deleteById(enterpriseId);
    }
}
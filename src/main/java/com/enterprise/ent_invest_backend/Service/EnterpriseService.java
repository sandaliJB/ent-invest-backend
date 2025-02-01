package com.enterprise.ent_invest_backend.Service;

import com.enterprise.ent_invest_backend.Model.Enterprise;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EnterpriseService {
    public Enterprise createEnterprise(Enterprise enterprise, MultipartFile imageFile) throws IOException;
    public Optional<Enterprise> getEnterpriseByEnterpriseId(String enterpriseId);
    public List<Enterprise> getEnterpriseByUserId(String userId);
    public List<Enterprise> getEnterpriseByName(String enterpriseName);
    public List<Enterprise> getEnterpriseByCity(String city);
    public List<Enterprise> getAllEnterprises();
    public Enterprise updateEnterprise(String enterpriseId, Enterprise enterpriseDetails);
    public void deleteEnterprise(String enterpriseId);
}

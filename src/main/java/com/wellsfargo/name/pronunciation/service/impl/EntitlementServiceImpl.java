package com.wellsfargo.name.pronunciation.service.impl;

import com.wellsfargo.name.pronunciation.entity.Entitlement;
import com.wellsfargo.name.pronunciation.repository.EntitlementRepository;
import com.wellsfargo.name.pronunciation.service.EntitlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntitlementServiceImpl implements EntitlementService {
    @Autowired
    EntitlementRepository entitlementRepository;

    @Override
    public List<Entitlement> getAllEntitlements() {
        return entitlementRepository.findAll();
    }
}

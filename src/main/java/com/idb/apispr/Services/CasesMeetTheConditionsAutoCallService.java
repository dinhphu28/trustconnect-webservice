package com.idb.apispr.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idb.apispr.Entities.CasesMeetTheConditionsAutoCall;
import com.idb.apispr.Repositories.CasesMeetTheConditionsAutoCallRepo;

@Service
public class CasesMeetTheConditionsAutoCallService {
    @Autowired
    private CasesMeetTheConditionsAutoCallRepo repo;

    public CasesMeetTheConditionsAutoCall retrieveByCampaignId(String campaignId) {
        CasesMeetTheConditionsAutoCall sth = null;

        try {
            sth = repo.findByCampaignId(campaignId);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sth;
    }
}

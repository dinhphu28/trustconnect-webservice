package com.idb.apispr.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idb.apispr.Repositories.CampaignStatisticRepo;

@Service
public class CampaignStatisticService {
    @Autowired
    private CampaignStatisticRepo repo;

    public Integer sumCallsByCampaignId(String campaignId) {
        Integer res = null;

        try {
            res = repo.sumCallsByCampaignId(campaignId);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return res;
    }

    public Integer countStatusAnswerInQNoAgent(String campaignId) {
        Integer res = null;

        try {
            res = repo.countStatusAnswerInQNoAgent(campaignId);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return res;
    }

    public Integer countFailToConnectCase(String campaignId) {
        Integer res = null;

        try {
            res = repo.countFailToConnectCase(campaignId);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return res;
    }
}

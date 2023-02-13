package com.idb.apispr.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idb.apispr.Entities.CampaignStatisticModel;
import com.idb.apispr.Services.CampaignStatisticService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/campaign-statistic")
public class CampaignStatisticController {
    @Autowired
    private CampaignStatisticService campaignStatisticService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll(@RequestParam("campaignId") List<String> campaignIds) {
        ResponseEntity<Object> entity;

        List<CampaignStatisticModel> campaignStatisticModels = new ArrayList<CampaignStatisticModel>();

        for (String campaignId : campaignIds) {
            Integer totalNumOfCallsMade = campaignStatisticService.sumCallsByCampaignId(campaignId);
            Integer totalNumOfStatusAnswerInQNoAgent = campaignStatisticService.countStatusAnswerInQNoAgent(campaignId);
            Integer totalFailToConnectCases = campaignStatisticService.countFailToConnectCase(campaignId);

            CampaignStatisticModel tmp = new CampaignStatisticModel();
            tmp.setId(campaignId);
            tmp.setTotalNumOfCallsMade(totalNumOfCallsMade);
            tmp.setTotalNumOfStatusAnswerInQNoAgent(totalNumOfStatusAnswerInQNoAgent);
            tmp.setTotalFailToConnectCases(totalFailToConnectCases);

            campaignStatisticModels.add(tmp);
        }

        entity = new ResponseEntity<>(campaignStatisticModels, HttpStatus.OK);

        return entity;
    }
}

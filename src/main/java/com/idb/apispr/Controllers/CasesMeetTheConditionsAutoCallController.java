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

import com.idb.apispr.Entities.CasesMeetTheConditionsAutoCall;
import com.idb.apispr.Models.CasesMeetTheConditionsAutoCall.CMTCACItemReturnModel;
import com.idb.apispr.Services.CasesMeetTheConditionsAutoCallService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auto-call-meet-conditions")
public class CasesMeetTheConditionsAutoCallController {
    @Autowired
    private CasesMeetTheConditionsAutoCallService casesMeetTheConditionsAutoCallService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveByCampaignId(@RequestParam(value = "campaignIds", required = true) List<String> campaignIds) {
        ResponseEntity<Object> entity;

        List<CMTCACItemReturnModel> cmtcacItemReturnModels = new ArrayList<CMTCACItemReturnModel>();

        for (String campaignId : campaignIds) {
            CasesMeetTheConditionsAutoCall casesMeetTheConditionsAutoCall = casesMeetTheConditionsAutoCallService.retrieveByCampaignId(campaignId);

            CMTCACItemReturnModel cmtcacItemReturnModel = new CMTCACItemReturnModel(campaignId, casesMeetTheConditionsAutoCall.getKq());

            cmtcacItemReturnModels.add(cmtcacItemReturnModel);
        }

        entity = new ResponseEntity<>(cmtcacItemReturnModels, HttpStatus.OK);

        return entity;
    }
}

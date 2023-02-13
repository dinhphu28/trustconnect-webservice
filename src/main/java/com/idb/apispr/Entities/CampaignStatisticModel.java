package com.idb.apispr.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampaignStatisticModel {
    private String id;

    private Integer totalNumOfCallsMade;

    private Integer totalNumOfStatusAnswerInQNoAgent;

    private Integer totalFailToConnectCases;
}

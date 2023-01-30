package com.idb.apispr.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.idb.apispr.Entities.CasesMeetTheConditionsAutoCall;

@Repository
public interface CasesMeetTheConditionsAutoCallRepo extends JpaRepository<CasesMeetTheConditionsAutoCall, Integer> {

    String query = "call api.auto_call_count_num_of_instances_meet_the_conditions('8070', :campaignid)";
    @Query(
        value = query,
        nativeQuery = true
    )
    CasesMeetTheConditionsAutoCall findByCampaignId(@Param("campaignid") String campaignId);
}

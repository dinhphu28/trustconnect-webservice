package com.idb.apispr.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.idb.apispr.Entities.IntegerEntity;

@Repository
public interface CampaignStatisticRepo extends JpaRepository<IntegerEntity, Integer> {
    
    @Query(
        value = "select sum(c_solan_call) from jwdb.app_fd_tls_chiendich_data where c_fkCD = ?1 and c_solan_call is not null",
        nativeQuery = true
    )
    Integer sumCallsByCampaignId(String campaignId);

    @Query(
        value = "select count(*) from jwdb.app_fd_tls_chiendich_data a join jwdb.app_fd_sp_calls b on a.c_call_id = b.c_call_id where a.c_fkCD = ?1 and b.c_status_log_text = 'Answered_inQueue_noAgent'",
        nativeQuery = true
    )
    Integer countStatusAnswerInQNoAgent(String campaignId);

    @Query(
        value = "select count(*) from jwdb.app_fd_tls_chiendich_data where c_fkCD = ?1 and coalesce(c_count_connected_times, '')*1 = 0 and coalesce(c_solan_call, '')*1 > 0",
        nativeQuery = true
    )
    Integer countFailToConnectCase(String campaignId);
}

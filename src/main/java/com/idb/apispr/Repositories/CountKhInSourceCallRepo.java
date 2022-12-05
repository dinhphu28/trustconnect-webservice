package com.idb.apispr.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.idb.apispr.Entities.CountKhInSourceCall;

@Repository
public interface CountKhInSourceCallRepo extends JpaRepository<CountKhInSourceCall, String> {
    @Query(
        value = "select api.tls_total_kh(?1) as nokh",
        nativeQuery = true
    )
    CountKhInSourceCall countKhInSourceCallById(String id);
}

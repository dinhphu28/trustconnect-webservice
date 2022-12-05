package com.idb.apispr.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idb.apispr.Entities.SourceCall;

@Repository
public interface SourceCallRepo extends JpaRepository<SourceCall, String> {
    List<SourceCall> findByLabel(String label);
}

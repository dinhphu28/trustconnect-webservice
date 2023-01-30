package com.idb.apispr.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.idb.apispr.Entities.ImpResult;

@Repository
public interface ImpResultRepo extends JpaRepository<ImpResult, String> {
    
    @Query(
        value = "call api.tls_imp_source_v2(?1, ?2, ?3, ?4)",
        nativeQuery = true
    )
    ImpResult execImportContacts(String inJsonContacts, String inUser, String inFullName, String inSource);

    @Query(
        value = "call api.tls_imp_source_v3(?1, ?2, ?3, ?4)",
        nativeQuery = true
    )
    ImpResult execImportContactsV3(String inJsonContacts, String inUser, String inFullName, String inSource);
}

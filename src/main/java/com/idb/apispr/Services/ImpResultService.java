package com.idb.apispr.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idb.apispr.Entities.ImpResult;
import com.idb.apispr.Repositories.ImpResultRepo;

@Service
public class ImpResultService {
    @Autowired
    private ImpResultRepo repo;

    public ImpResult execImportContacts(String inJsonContacts, String inUser, String inFullName, String inSource) {
        ImpResult impResult = null;

        try {
            impResult = repo.execImportContacts(inJsonContacts, inUser, inFullName, inSource);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return impResult;
    }
}

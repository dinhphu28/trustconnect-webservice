package com.idb.apispr.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idb.apispr.Entities.SourceCall;
import com.idb.apispr.Repositories.SourceCallRepo;

@Service
public class SourceCallService {
    @Autowired
    private SourceCallRepo repo;

    public List<SourceCall> retrieveAll() {
        return repo.findAll();
    }

    public SourceCall retrieveById(String id) {
        SourceCall tmp = null;

        try {
            tmp = repo.findById(id).get();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }

    public List<SourceCall> retrieveByLabel(String label) {
        return repo.findByLabel(label);
    }
}

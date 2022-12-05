package com.idb.apispr.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idb.apispr.Entities.CountKhInSourceCall;
import com.idb.apispr.Repositories.CountKhInSourceCallRepo;

@Service
public class CountKhInSourceCallService {
    @Autowired
    private CountKhInSourceCallRepo repo;

    public CountKhInSourceCall retrieveBySourceCallId(String id) {
        CountKhInSourceCall tmp = null;

        try {
            tmp = repo.countKhInSourceCallById(id);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return tmp;
    }
}

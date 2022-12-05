package com.idb.apispr.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.apispr.Entities.SourceCall;
import com.idb.apispr.Models.NoticeModel;
import com.idb.apispr.Services.SourceCallService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/source-calls")
public class SourceCallController {
    @Autowired
    private SourceCallService sourceCallService;

    @Value("${idb.external.apikey}")
    private String localApiKey;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveAll(@RequestHeader(value = "X-API-KEY", required = true) String apiKey) {
        ResponseEntity<Object> entity;

        if(apiKey.equals(localApiKey)) {
            List<SourceCall> sourceCalls = sourceCallService.retrieveAll();

            entity = new ResponseEntity<>(sourceCalls, HttpStatus.OK);
        } else {
            entity = new ResponseEntity<>(new NoticeModel("Unauthorized", "Invalid API Key"), HttpStatus.UNAUTHORIZED);
        }

        return entity;
    }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveById(@PathVariable("id") String id, @RequestHeader(value = "X-API-KEY", required = true) String apiKey) {
        ResponseEntity<Object> entity;

        if(apiKey.equals(localApiKey)) {
            SourceCall sourceCall = sourceCallService.retrieveById(id);

            if(sourceCall == null) {
                entity = new ResponseEntity<>(new NoticeModel("Not found", "Source call does not exist"), HttpStatus.NOT_FOUND);
            } else {
                entity = new ResponseEntity<>(sourceCall, HttpStatus.OK);
            }
        } else {
            entity = new ResponseEntity<>(new NoticeModel("Unauthorized", "Invalid API Key"), HttpStatus.UNAUTHORIZED);
        }

        return entity;
    }
}

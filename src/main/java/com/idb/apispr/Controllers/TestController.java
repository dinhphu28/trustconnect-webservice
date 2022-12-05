package com.idb.apispr.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.apispr.Entities.CountKhInSourceCall;
import com.idb.apispr.Models.NoticeModel;
import com.idb.apispr.Services.CountKhInSourceCallService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/tests")
public class TestController {
    @Autowired
    private CountKhInSourceCallService countKhInSourceCallService;

    @GetMapping(
        value = "{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> retrieveX(@PathVariable("id") String id) {
        ResponseEntity<Object> entity;

        CountKhInSourceCall call = countKhInSourceCallService.retrieveBySourceCallId(id);

        if(call == null) {
            entity = new ResponseEntity<>(new NoticeModel("Not found", "Source call does not exist"), HttpStatus.NOT_FOUND);
        } else {
            entity = new ResponseEntity<>(call, HttpStatus.OK);
        }

        return entity;
    }
}

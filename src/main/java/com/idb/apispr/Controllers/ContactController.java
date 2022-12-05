package com.idb.apispr.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idb.apispr.Entities.CountKhInSourceCall;
import com.idb.apispr.Entities.ImpResult;
import com.idb.apispr.Entities.SourceCall;
import com.idb.apispr.Models.ContactModel;
import com.idb.apispr.Models.ImpContactsPayloadModel;
import com.idb.apispr.Models.ImportResultModel;
import com.idb.apispr.Models.NoticeModel;
import com.idb.apispr.Services.CountKhInSourceCallService;
import com.idb.apispr.Services.ImpResultService;
import com.idb.apispr.Services.SourceCallService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/contacts")
public class ContactController {
    @Autowired
    private ImpResultService impResultService;

    @Autowired
    private CountKhInSourceCallService countKhInSourceCallService;

    @Autowired
    private SourceCallService sourceCallService;

    @Value("${idb.external.apikey}")
    private String localApiKey;
    
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> createBatchContacts(@RequestBody ImpContactsPayloadModel contactModel, @RequestHeader(value = "X-API-KEY", required = true) String apiKey) {
        ResponseEntity<Object> entity;

        if(apiKey.equals(localApiKey)) {
            SourceCall sourceCall = sourceCallService.retrieveById(contactModel.getSourceCall());

            if(sourceCall != null) {

                ObjectMapper objectMapper = new ObjectMapper();

                String jsonCM = "";

                ImpResult impResult = null;

                CountKhInSourceCall callBef = countKhInSourceCallService.retrieveBySourceCallId(contactModel.getSourceCall());

                try {
                    jsonCM = objectMapper.writeValueAsString(contactModel.getContacts());

                    impResult = impResultService.execImportContacts(jsonCM, "clientapi", "Client API", contactModel.getSourceCall());
                } catch (Exception e) {
                    // TODO: handle exception
                }

                if(impResult != null) {

                    if(impResult.getRs().equals("ok")) {
                        CountKhInSourceCall callAft = countKhInSourceCallService.retrieveBySourceCallId(contactModel.getSourceCall());

                        try {
                            int befQuantity = Integer.parseInt(callBef.getNoKH());
                            int aftQuantity = Integer.parseInt(callAft.getNoKH());

                            ImportResultModel resultImport = new ImportResultModel(befQuantity, aftQuantity);

                            entity = new ResponseEntity<>(resultImport, HttpStatus.CREATED);
                        } catch (Exception e) {
                            // TODO: handle exception
                            entity = new ResponseEntity<>(new NoticeModel("Internal Server Error", "Cannot get number of contact imported"), HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        entity = new ResponseEntity<>(new NoticeModel("Internal Server Error", "Cannot import data"), HttpStatus.INTERNAL_SERVER_ERROR);
                    }

                    // entity = new ResponseEntity<>(impResult, HttpStatus.OK);
                } else {
                    // entity = new ResponseEntity<>("{\"Hell\": \"Failed\"}", HttpStatus.OK);
                    entity = new ResponseEntity<>(new NoticeModel("Internal Server Error", "Cannot import data"), HttpStatus.INTERNAL_SERVER_ERROR);
                }
                
            } else {
                entity = new ResponseEntity<>(new NoticeModel("Not found", "Source Call (ID) does not exist"), HttpStatus.NOT_FOUND);
            }
        } else {
            entity = new ResponseEntity<>(new NoticeModel("Unauthorized", "Invalid API Key"), HttpStatus.UNAUTHORIZED);
        }
        return entity;
    }
}

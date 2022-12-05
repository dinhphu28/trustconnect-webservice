package com.idb.apispr.Models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImpContactsPayloadModel {
    private List<ContactModel> contacts;

    // private String username;

    // private String fullName;

    private String sourceCall;
}

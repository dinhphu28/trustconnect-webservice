package com.idb.apispr.Configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info (
        title = "TrustConnect API Spec",
        description = "For client",
        contact = @Contact(
            name = "Anthony",
            url = "https://idb.com.vn",
            email = "tony@idb.com.vn"
        ),
        license = @License(
            name = "MIT license",
            url = "https://idb.com.vn/license"
        )
    ),
    servers = {
        @Server(
            url = "http://103.146.21.221:8080/apispr",
            description = "Run on production with IP Address"
        ),
        @Server(
            url = "https://crm-trustconnect.dxws.io/apispr",
            description = "Run on production with domain name"
        ),
        @Server(
        url = "http://localhost:8080",
        description = "Run on localhost"
        )
    }
)
public class SpringDocConfig {
    
}
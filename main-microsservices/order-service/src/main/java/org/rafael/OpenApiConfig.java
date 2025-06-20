package org.rafael;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "API Order Service from Microsservices with Quarkus",
                version = "1.0",
                contact = @Contact(name = "Rafael")
        )
)
public class OpenApiConfig extends Application {
}
package com.distribuida;
import jakarta.ws.rs.ApplicationPath;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;


@ApplicationPath("/")
@OpenAPIDefinition(info = @Info(
        title = "Diego Gualoto / app-book",
        version = "1.0.0"

))
public class RestApp extends Application {

}

package com.programacion.distribuida.rest;

import com.programacion.distribuida.db.Author;
import com.programacion.distribuida.repository.AuthorRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorRest {

    @Inject AuthorRepository authorepository;

    @GET
    @Path("/{id}")
    @Operation(summary = " BUSCAR UN AUTOR POR ID ")
    @APIResponse(responseCode = "200", description = "SE ENCONTRO EL AUTOR EXITOSAMENTE", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class)))
    @APIResponse(responseCode = "400", description = "ID DE AUTOR NO ENCONTRADO ")
    public Author findById(@PathParam("id") Long id) {
        return authorepository.findById(id);
    }
    
    @GET
    @Operation(summary = "CONSULTAR TODOS LOS AUTORES")
    @APIResponse(responseCode = "200", description = "CONSULTAR TODOS LOS AUTORES", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Author.class)))
    public List<Author> findAll() {
        return authorepository
                .findAll()
                .list();

    }
    @PUT
    @Path("/{id}")
    @Operation(description = "ACTUALIZAR UN AUTOR")
    @APIResponse(responseCode = "200", description = "EL AUTOR SE HA CREADO CORRECTAMENTE")
    public void update(Author obj, @PathParam("id") Long id) {
        var author = authorepository.findById(id);
        author.setFirst_name(obj.getFirst_name());
        author.setLast_name(obj.getLast_name());

    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "ELIMINAR UN AUTOR POR ID")
    @APIResponse(responseCode = "204",description = "EL AUTOR SE HA ELIMINADO CORRECTAMENTE ")
    public void delete( @PathParam("id") Long id ) {
        authorepository.deleteById(id);
    }

    @POST
    @Operation(description = "INSERTAR UN AUTOR ")
    @APIResponse(responseCode = "201", description = "EL AUTOR FUE INSERTADO CORRECTAMENTE")
    public void insert(Author obj) {
        authorepository.persist(obj);
    }




}

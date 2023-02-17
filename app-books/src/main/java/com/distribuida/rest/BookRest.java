package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject
    private BookService bookService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "OBTENER UN LIBRO POR ID")
    @APIResponse(responseCode = "200", description = "SE ENCONTRO EL LIBRO POR ID", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)))
    @APIResponse(responseCode = "400", description = "EL ID DEL LIBRO NO EXISTE")
    public Book findById(@Parameter(description = "ID", required = true) @PathParam("id") Integer id)  {
        return bookService.findId(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "CONSULTAR TODOS LOS LIBROS")
    @APIResponse(responseCode = "200", description = "CONSULTAR TODOS LOS LIBROS", content =
    @Content(mediaType = "application/json", schema = @Schema(allOf = Book.class)))
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @DELETE
    @Path("/{id}")
    @Operation(description = "ELIMINAR UN LIBRO POR ID ")
    @APIResponse(responseCode = "204", description = "EL LIBRO SE A ELIMINADO CORRECTAMENTE")
    public Response delete(
            @Parameter(description = "ID", required = true)
            @PathParam("id") Integer id) {
        bookService.delete(id);
        return Response.status((Response.Status.NO_CONTENT)).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(description = "ACTUALIZAR UN LIBRO")
    @APIResponse(responseCode = "200", description = "EL LIBRO SE ACTUALIZO EXITOSAMENTE")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(
            @RequestBody(description = "BOOK", required = true,
                    content = @Content(schema = @Schema(implementation = Book.class)))
            Book b,
            @Parameter(description = "ID", required = true)
            @PathParam("id") Integer id){
        bookService.update(id, b);
        return Response.status((Response.Status.OK)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(description = "INSERTAR UN LIBRO")
    @APIResponse(responseCode = "201", description = "EL LIBRO SE INSERTO EXITOSAMENTE")
    public Response insert(
            @RequestBody(description = "BOOK", required = true,
                    content = @Content(schema = @Schema(implementation = Book.class)))
            Book b){
        bookService.insert(b);
        return Response.status(Response.Status.CREATED).build();
    }



}

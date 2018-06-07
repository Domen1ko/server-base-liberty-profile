package com.tmajor.server.application.rest;

import com.tmajor.server.integration.controller.NodeRegisterController;
import com.tmajor.server.integration.model.NodeDetail;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/discovery")
public class DiscoveryResource {

    @Context
    HttpServletRequest request;

    @Inject
    private
    NodeRegisterController controller;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {
        return Response.ok().build();
    }


    @APIResponses(value = {
            @APIResponse(
                    responseCode = "404",
                    description = "Missing description",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON)),
            @APIResponse(
                    responseCode = "200",
                    description = "JVM system properties of a particular host.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NodeDetail.class)))})
    @Operation(
            summary = "Register new client",
            description = "this method allow client to register to central ")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@NotNull NodeDetail detail) {
        String remoteAddr = request.getRemoteAddr();
        detail.setAddress(remoteAddr);
        controller.registerApplication(detail);
        return Response.ok().build();
    }
}

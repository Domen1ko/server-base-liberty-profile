package com.tmajor.server.application.rest;

import com.tmajoir.lib.core.error.TMajorException;
import com.tmajor.lib.log.logger.TMajorLogger;
import com.tmajor.server.integration.controller.AuthorizationController;
import com.tmajor.server.integration.model.User;
import com.tmajor.server.integration.providers.PasswordVerification;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/auth")
public class AuthorizationResource {

    public static final TMajorLogger logger = TMajorLogger.getLogger("TJ");


    @Context
    HttpServletRequest request;

    @Inject
    private
    AuthorizationController userController;

    @POST
    @Path("/sing-in")
    public Response singIn(@NotNull User user) throws TMajorException {
        User backEndUser = userController.get(user.getUserId());
        PasswordVerification.checkPassword(user.getPassword(), backEndUser.getPassword());
        // at this point you are authenticated
        return Response.ok().build();
    }

    @POST
    @Path("/sign-up")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(@NotNull User user) throws TMajorException {
        if (PasswordVerification.checkComplexity(user.getPassword())) {
            user.setPassword(PasswordVerification.encrypt(user.getPassword()));
        } else
            throw new TMajorException("400", logger.error("TJ", user.getUserId(), null, "Password is not strong enaugh"));
        userController.signUp(user);
        return Response.ok().build();
    }

}

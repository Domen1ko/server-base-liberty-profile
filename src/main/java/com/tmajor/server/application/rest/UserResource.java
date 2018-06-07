package com.tmajor.server.application.rest;

import com.tmajoir.lib.core.error.TMajorException;
import com.tmajor.lib.log.logger.TMajorLogger;
import com.tmajor.server.integration.controller.UserController;
import com.tmajor.server.integration.model.User;
import com.tmajor.server.integration.providers.PasswordVerification;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/users")
public class UserResource {

    public static final TMajorLogger logger = TMajorLogger.getLogger("TJ");

    @Inject
    UserController userController;


}

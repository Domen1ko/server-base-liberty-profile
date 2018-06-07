package com.tmajor.server.integration.controller;


import com.tmajoir.lib.core.error.TMajorException;
import com.tmajor.lib.log.logger.TMajorLogger;
import com.tmajor.server.integration.controller.abstracts.TJNoSQLCrudRepository;
import com.tmajor.server.integration.model.User;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserController extends TJNoSQLCrudRepository<User> {

    private static final TMajorLogger logger = TMajorLogger.getLogger("TJ");

    private static final String BUCKET = "user";

    public UserController() {
        super(BUCKET);
    }


}

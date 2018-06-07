package com.tmajor.server.integration.controller;

import com.couchbase.client.java.CouchbaseCluster;
import com.tmajoir.lib.core.error.TMajorException;
import com.tmajor.lib.log.logger.TMajorLogger;
import com.tmajor.server.integration.controller.abstracts.TJNoSQLCrudRepository;
import com.tmajor.server.integration.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@RequestScoped
public class AuthorizationController extends TJNoSQLCrudRepository<User> {

    private static final String BUCKET = "auth";
    private static final TMajorLogger logger = TMajorLogger.getLogger("TJ");

    @Inject
    CouchbaseCluster cluser;

    public AuthorizationController() {
        super(BUCKET);
    }

    public void signUp(@NotNull User user) throws TMajorException {
        if (get(user.getUserId()) == null) {
            upsert(user.getUserId(), user);
        } else throw new TMajorException("409", logger.error("TJ", user.getUserId(), null, "User name already in use"));
    }

}

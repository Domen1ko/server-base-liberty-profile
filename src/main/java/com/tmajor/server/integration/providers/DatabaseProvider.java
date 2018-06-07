package com.tmajor.server.integration.providers;


import com.couchbase.client.java.CouchbaseCluster;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import java.io.Serializable;

@RequestScoped
public class DatabaseProvider implements Serializable {

    private static CouchbaseCluster cluster;


    @Produces
    public CouchbaseCluster cluster() {
        if (cluster == null) {
            cluster = CouchbaseCluster.create("127.0.0.1");
            cluster.authenticate("node", "nodenode");

        }
        return cluster;
    }
}

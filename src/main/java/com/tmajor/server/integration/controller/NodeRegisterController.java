package com.tmajor.server.integration.controller;


import com.couchbase.client.java.CouchbaseCluster;
import com.tmajor.server.integration.model.NodeDetail;
import com.tmajor.server.integration.resource.TJBucket;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class NodeRegisterController {

    private static final String BUCKET = "node";

    @Inject
    CouchbaseCluster cluser;

    public void registerApplication(NodeDetail node) {
        try (TJBucket<NodeDetail> buk = new TJBucket<>(cluser, BUCKET)) {
            buk.upsert(node.getId(), node);
        }
    }
}

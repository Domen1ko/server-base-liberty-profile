package com.tmajor.server.integration.controller.abstracts;

import com.couchbase.client.java.CouchbaseCluster;
import com.tmajor.server.integration.resource.TJBucket;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public abstract class TJNoSQLCrudRepository<T> {

    @Inject
    CouchbaseCluster cluser;

    private String bucket;

    public TJNoSQLCrudRepository(String bucket) {
        this.bucket = bucket;
    }

    public TJNoSQLCrudRepository() {
    }


    public String upsert(T item) {
        String uuid = UUID.randomUUID().toString();
        return upsert(uuid, item);
    }

    public String upsert(String id, T item) {
        try (TJBucket<T> buk = new TJBucket<>(cluser, bucket)) {
            buk.upsert(id, item);
        }
        return id;
    }


    public T get(@NotNull String id) {
        try (TJBucket<T> buk = new TJBucket<>(cluser, bucket)) {
            return buk.get(id);
        }
    }


}

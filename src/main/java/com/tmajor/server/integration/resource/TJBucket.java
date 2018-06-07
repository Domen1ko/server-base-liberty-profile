package com.tmajor.server.integration.resource;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.bucket.BucketType;
import com.couchbase.client.java.cluster.BucketSettings;
import com.couchbase.client.java.cluster.ClusterManager;
import com.couchbase.client.java.cluster.DefaultBucketSettings;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.error.BucketDoesNotExistException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.validation.constraints.NotNull;

public class TJBucket<T> implements AutoCloseable {

    private final Gson gson = new Gson();
    private Bucket bucket;

    public TJBucket(@NotNull CouchbaseCluster cluster, @NotNull String name) {
        try {
            this.bucket = cluster.openBucket(name);

        } catch (BucketDoesNotExistException e) {
            ClusterManager clusterManager = cluster.clusterManager("node", "nodenode");
            BucketSettings bucketSettings = new DefaultBucketSettings.Builder()
                    .type(BucketType.COUCHBASE)
                    .name(name)
                    .quota(200)
                    .build();

            clusterManager.insertBucket(bucketSettings);
            this.bucket = cluster.openBucket(name);
        }
    }

    public T upsert(String id, T value) {
        JsonObject obj = JsonObject.fromJson(gson.toJson(value));
        JsonDocument doc = JsonDocument.create(id, obj);
        JsonDocument done = bucket.upsert(doc);
        return (T) gson.fromJson(done.content().toString(), value.getClass());
    }


    public T get(String id) {
        JsonDocument done = bucket.get(id);
        if (done != null) {
            return (T) gson.fromJson(done.content().toString(), new TypeToken<T>() {
            }.getType());
        } else return null;
    }


    @Override
    public void close() {
        if (bucket != null) {
            bucket.close();
        }
    }
}

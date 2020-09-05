package com.baeldung;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.MutationResult;
import com.couchbase.client.java.query.QueryResult;

class StartUsing {

    public static void main(String [] args) {
        Cluster cluster = Cluster.connect("localhost", "admin", "admin123");
        Bucket bucket = cluster.bucket("demo");
        Collection collection = bucket.defaultCollection();

        MutationResult upsertResult = collection.upsert(
                "my-document11",
                JsonObject.create().put("name", "mike")
        );
        collection.remove("my-document1");
        GetResult getResult = collection.get("my-document11");
        String name = getResult.contentAsObject().getString("name");
        System.out.println(name); // name == "mike"

        QueryResult result = cluster.query("select \"Hello World\" as greeting");
        System.out.println(result.rowsAsObject());
    }

}

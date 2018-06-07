package com.tmajor.server.integration.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "NodeDetail", description = "Base Node Detail")
public class NodeDetail {

    @Schema(required = true, name = "name", example = "nodeA1", description = "Node name")
    private String name;
    @Schema(name = "id", description = "unique id given to node at the moment of registration")
    private String id;
    @Schema(name = "address")
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

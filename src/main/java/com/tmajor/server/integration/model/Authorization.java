package com.tmajor.server.integration.model;

import java.util.List;
import java.util.Map;

public class Authorization {

    private List<String> roles;
    private String id;
    private String profile;
    private String nodeReference;
    private Map<String,String> params;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getNodeReference() {
        return nodeReference;
    }

    public void setNodeReference(String nodeReference) {
        this.nodeReference = nodeReference;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}

package com.mparticle;

import org.json.JSONObject;

public class DeepLinkResult {
    private String linkUrl = null;
    private JSONObject parameters;
    private int serviceProviderId;

    public DeepLinkResult setParameters(JSONObject parameters2) {
        this.parameters = parameters2;
        return this;
    }

    public JSONObject getParameters() {
        return this.parameters;
    }

    public DeepLinkResult setLink(String linkUrl2) {
        this.linkUrl = linkUrl2;
        return this;
    }

    public String getLink() {
        return this.linkUrl;
    }

    public DeepLinkResult setServiceProviderId(int id) {
        this.serviceProviderId = id;
        return this;
    }

    public int getServiceProviderId() {
        return this.serviceProviderId;
    }

    public String toString() {
        boolean z = false;
        StringBuilder sb = new StringBuilder("Deep Link Result:\n");
        boolean z2 = true;
        if (this.serviceProviderId > 0) {
            sb.append("Service provider ID:\n").append(this.serviceProviderId).append("\n");
            z2 = false;
        }
        if (this.linkUrl != null) {
            sb.append("Link URL:\n").append(this.linkUrl).append("\n");
            z2 = false;
        }
        if (this.parameters != null) {
            sb.append("Link parameters:\n").append(this.parameters.toString()).append("\n");
        } else {
            z = z2;
        }
        if (z) {
            sb.append("Empty");
        }
        return sb.toString();
    }
}

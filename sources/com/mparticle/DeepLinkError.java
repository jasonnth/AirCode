package com.mparticle;

public class DeepLinkError {
    private String message;
    private int serviceProviderId;

    public DeepLinkError setMessage(String message2) {
        this.message = message2;
        return this;
    }

    public DeepLinkError setServiceProviderId(int id) {
        this.serviceProviderId = id;
        return this;
    }

    public int getServiceProviderId() {
        return this.serviceProviderId;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        boolean z = false;
        StringBuilder sb = new StringBuilder("Deep Link Error:\n");
        boolean z2 = true;
        if (this.serviceProviderId > 0) {
            sb.append("Service provider ID:\n").append(this.serviceProviderId).append("\n");
            z2 = false;
        }
        if (this.message != null) {
            sb.append("Message:\n").append(this.message).append("\n");
        } else {
            z = z2;
        }
        if (z) {
            sb.append("Empty error");
        }
        return sb.toString();
    }
}

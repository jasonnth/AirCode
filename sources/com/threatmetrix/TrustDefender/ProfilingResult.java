package com.threatmetrix.TrustDefender;

public class ProfilingResult {

    /* renamed from: a */
    private String f4095a;

    /* renamed from: b */
    private THMStatusCode f4096b;

    ProfilingResult(String sessionID, THMStatusCode status) {
        this.f4095a = sessionID;
        this.f4096b = status;
    }

    public String getSessionID() {
        return this.f4095a;
    }

    public THMStatusCode getStatus() {
        return this.f4096b;
    }
}

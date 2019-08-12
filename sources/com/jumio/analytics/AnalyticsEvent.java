package com.jumio.analytics;

import java.util.UUID;

public class AnalyticsEvent {
    protected int mEventType;
    protected Payload mPayload;
    protected UUID mSessionId;
    protected final long mTimestamp = System.currentTimeMillis();

    public AnalyticsEvent() {
    }

    public AnalyticsEvent(int type, UUID sessionId, MetaInfo valueAsMap) {
        this.mSessionId = sessionId;
        this.mEventType = type;
        this.mPayload = new Payload(valueAsMap, null);
    }

    public AnalyticsEvent(int type, UUID sessionId, String payloadValue, MetaInfo payloadMetaInfo) {
        this.mEventType = type;
        this.mSessionId = sessionId;
        this.mPayload = new Payload(payloadValue, payloadMetaInfo);
    }

    public String toString() {
        return "AnalyticsEvent " + this.mEventType + " | " + this.mTimestamp + " // " + this.mPayload.getValue() + (this.mPayload.getMetaInfo() != null ? " | " + this.mPayload.getMetaInfo() : "");
    }

    public int hashCode() {
        return ((((((int) (this.mTimestamp ^ (this.mTimestamp >>> 32))) + 31) * 31) + (this.mSessionId == null ? 0 : this.mSessionId.hashCode())) * 31) + this.mEventType;
    }

    public boolean equals(Object o) {
        if (!(o instanceof AnalyticsEvent)) {
            return false;
        }
        AnalyticsEvent other = (AnalyticsEvent) o;
        if (other.mTimestamp == this.mTimestamp && other.mSessionId.equals(this.mSessionId) && other.mEventType == this.mEventType) {
            return true;
        }
        return false;
    }

    public int getEventType() {
        return this.mEventType;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public Payload getPayload() {
        return this.mPayload;
    }

    public UUID getSessionId() {
        return this.mSessionId;
    }
}

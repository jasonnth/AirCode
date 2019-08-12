package com.airbnb.p027n2.transitions;

/* renamed from: com.airbnb.n2.transitions.AutoValue_TransitionName */
final class AutoValue_TransitionName extends TransitionName {

    /* renamed from: id */
    private final long f2708id;
    private final long subId;
    private final String subtype;
    private final String type;

    AutoValue_TransitionName(String type2, long id, String subtype2, long subId2) {
        if (type2 == null) {
            throw new NullPointerException("Null type");
        }
        this.type = type2;
        this.f2708id = id;
        if (subtype2 == null) {
            throw new NullPointerException("Null subtype");
        }
        this.subtype = subtype2;
        this.subId = subId2;
    }

    public String type() {
        return this.type;
    }

    /* renamed from: id */
    public long mo26441id() {
        return this.f2708id;
    }

    public String subtype() {
        return this.subtype;
    }

    public long subId() {
        return this.subId;
    }

    public String toString() {
        return "TransitionName{type=" + this.type + ", id=" + this.f2708id + ", subtype=" + this.subtype + ", subId=" + this.subId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TransitionName)) {
            return false;
        }
        TransitionName that = (TransitionName) o;
        if (!this.type.equals(that.type()) || this.f2708id != that.mo26441id() || !this.subtype.equals(that.subtype()) || this.subId != that.subId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (((long) (((((int) (((long) (((1 * 1000003) ^ this.type.hashCode()) * 1000003)) ^ ((this.f2708id >>> 32) ^ this.f2708id))) * 1000003) ^ this.subtype.hashCode()) * 1000003)) ^ ((this.subId >>> 32) ^ this.subId));
    }
}

package com.airbnb.android.aireventlogger;

import java.lang.reflect.Type;

public final class AirEvent<T> {
    private final T data;
    private final Type eventType;

    /* renamed from: id */
    private final int f2059id;
    private final String target;

    private AirEvent(int id, T data2, String target2) {
        this.f2059id = id;
        this.data = data2;
        this.eventType = data2.getClass();
        this.target = target2;
    }

    public AirEvent(T data2) {
        this(-1, data2, null);
    }

    public AirEvent(T data2, String target2) {
        this(-1, data2, target2);
    }

    public T data() {
        return this.data;
    }

    /* renamed from: id */
    public int mo19312id() {
        return this.f2059id;
    }

    public String target() {
        return this.target;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AirEvent)) {
            return false;
        }
        AirEvent airEvent = (AirEvent) o;
        if (this.target == null ? airEvent.target != null : !this.target.equals(airEvent.target)) {
            return false;
        }
        if (this.data != null) {
            if (this.data.equals(airEvent.data)) {
                return true;
            }
        } else if (airEvent.data == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.data != null) {
            return this.data.hashCode();
        }
        return 0;
    }

    public Type getEventType() {
        return this.eventType;
    }
}

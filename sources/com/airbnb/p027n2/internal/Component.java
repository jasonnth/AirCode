package com.airbnb.p027n2.internal;

import com.airbnb.p027n2.DLSComponentType;

/* renamed from: com.airbnb.n2.internal.Component */
public abstract class Component {
    public abstract String name();

    public abstract DLSComponentType type();

    public static Component create(String name, DLSComponentType type) {
        return new AutoValue_Component(name, type);
    }
}

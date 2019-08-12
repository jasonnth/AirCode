package com.jumio.p311nv.models;

import com.jumio.commons.PersistWith;
import com.jumio.core.mvp.model.StaticModel;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

@PersistWith("BackendModel")
/* renamed from: com.jumio.nv.models.BackendModel */
public class BackendModel implements StaticModel {

    /* renamed from: a */
    private HashMap<Class<?>, Serializable> f3385a = new LinkedHashMap();

    public void add(Class<?> cls, Serializable serializable) {
        this.f3385a.put(cls, serializable);
    }

    public boolean has(Class<?> cls) {
        return this.f3385a.containsKey(cls);
    }

    public Object get(Class<?> cls) {
        return this.f3385a.get(cls);
    }

    public void remove(Class<?> cls) {
        this.f3385a.remove(cls);
    }

    public void clear() {
        this.f3385a.clear();
    }
}

package com.jumio.p311nv.models;

import com.jumio.commons.PersistWith;
import com.jumio.core.mvp.model.StaticModel;

@PersistWith("InitiateModel")
/* renamed from: com.jumio.nv.models.InitiateModel */
public class InitiateModel implements StaticModel {

    /* renamed from: a */
    private String f3388a;

    public String getJumioScanRef() {
        return this.f3388a;
    }

    public void setJumioScanRef(String str) {
        this.f3388a = str;
    }
}

package com.jumio.p311nv.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.jumio.commons.PersistWith;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.p311nv.NetverifyDocumentData;

@PersistWith("LivenessModel")
/* renamed from: com.jumio.nv.models.LivenessModel */
public class LivenessModel extends NetverifyDocumentData implements StaticModel {
    public static final Creator<LivenessModel> CREATOR = new Creator<LivenessModel>() {
        /* renamed from: a */
        public LivenessModel createFromParcel(Parcel parcel) {
            return new LivenessModel(parcel);
        }

        /* renamed from: a */
        public LivenessModel[] newArray(int i) {
            return new LivenessModel[i];
        }
    };

    /* renamed from: a */
    private Boolean f3389a = null;

    public LivenessModel() {
    }

    public LivenessModel(Parcel parcel) {
        super(parcel);
    }

    public Boolean getLivenessDetected() {
        return this.f3389a;
    }

    public void setLivenessDetected(Boolean bool) {
        this.f3389a = bool;
    }
}

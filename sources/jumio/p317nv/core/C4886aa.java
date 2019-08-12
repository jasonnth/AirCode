package jumio.p317nv.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.jumio.p311nv.models.DocumentDataModel;

/* renamed from: jumio.nv.core.aa */
/* compiled from: LivenessDataModel */
public class C4886aa extends DocumentDataModel {
    public static final Creator<C4886aa> CREATOR = new Creator<C4886aa>() {
        /* renamed from: a */
        public C4886aa createFromParcel(Parcel parcel) {
            return new C4886aa(parcel);
        }

        /* renamed from: a */
        public C4886aa[] newArray(int i) {
            return new C4886aa[i];
        }
    };

    /* renamed from: a */
    private String[] f4705a;

    public C4886aa() {
    }

    public C4886aa(Parcel parcel) {
        super(parcel);
        this.f4705a = parcel.createStringArray();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeStringArray(this.f4705a);
    }

    /* renamed from: a */
    public String[] mo46795a() {
        return this.f4705a;
    }

    /* renamed from: a */
    public void mo46794a(String[] strArr) {
        this.f4705a = strArr;
    }
}

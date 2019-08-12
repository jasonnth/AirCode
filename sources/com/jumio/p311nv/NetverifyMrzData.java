package com.jumio.p311nv;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.jumio.p311nv.data.document.NVMRZFormat;
import java.io.Serializable;

/* renamed from: com.jumio.nv.NetverifyMrzData */
public class NetverifyMrzData implements Parcelable, Serializable {
    public static final Creator<NetverifyMrzData> CREATOR = new Creator<NetverifyMrzData>() {
        /* renamed from: a */
        public NetverifyMrzData[] newArray(int i) {
            return new NetverifyMrzData[i];
        }

        /* renamed from: a */
        public NetverifyMrzData createFromParcel(Parcel parcel) {
            return new NetverifyMrzData(parcel);
        }
    };
    protected boolean mCompositeValid = false;
    protected boolean mDobValid;
    protected boolean mExpiryValid;
    protected NVMRZFormat mFormat = NVMRZFormat.MRP;
    protected boolean mIdValid;
    protected boolean mPersNumValid = true;
    protected String mrzLine1;
    protected String mrzLine2;
    protected String mrzLine3;

    public NetverifyMrzData() {
    }

    public NetverifyMrzData(Parcel parcel) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        this.mrzLine1 = parcel.readString();
        this.mrzLine2 = parcel.readString();
        this.mrzLine3 = parcel.readString();
        try {
            this.mFormat = NVMRZFormat.values()[parcel.readInt()];
        } catch (Exception e) {
            e.printStackTrace();
            this.mFormat = NVMRZFormat.MRP;
        }
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.mDobValid = z;
        if (parcel.readInt() == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.mExpiryValid = z2;
        if (parcel.readInt() == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.mCompositeValid = z3;
        if (parcel.readInt() == 1) {
            z4 = true;
        } else {
            z4 = false;
        }
        this.mIdValid = z4;
        if (parcel.readInt() != 1) {
            z5 = false;
        }
        this.mPersNumValid = z5;
    }

    public String getMrzLine1() {
        return this.mrzLine1;
    }

    public void setMrzLine1(String str) {
        this.mrzLine1 = str;
    }

    public String getMrzLine2() {
        return this.mrzLine2;
    }

    public void setMrzLine2(String str) {
        this.mrzLine2 = str;
    }

    public String getMrzLine3() {
        return this.mrzLine3;
    }

    public void setMrzLine3(String str) {
        this.mrzLine3 = str;
    }

    public boolean idNumberValid() {
        return this.mIdValid;
    }

    public void setIdNumberValid(boolean z) {
        this.mIdValid = z;
    }

    public boolean dobValid() {
        return this.mDobValid;
    }

    public void setDobValid(boolean z) {
        this.mDobValid = z;
    }

    public boolean expiryDateValid() {
        return this.mExpiryValid;
    }

    public void setExpiryDateValid(boolean z) {
        this.mExpiryValid = z;
    }

    public boolean personalNumberValid() {
        return this.mPersNumValid;
    }

    public void setPersonalNumberValid(boolean z) {
        this.mPersNumValid = z;
    }

    public boolean compositeValid() {
        return this.mCompositeValid;
    }

    public void setCompositeValid(boolean z) {
        this.mCompositeValid = z;
    }

    public NVMRZFormat getFormat() {
        return this.mFormat;
    }

    public void setFormat(NVMRZFormat nVMRZFormat) {
        this.mFormat = nVMRZFormat;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3;
        int i4;
        int i5 = 1;
        parcel.writeString(this.mrzLine1);
        parcel.writeString(this.mrzLine2);
        parcel.writeString(this.mrzLine3);
        parcel.writeInt(this.mFormat.ordinal());
        parcel.writeInt(this.mDobValid ? 1 : 0);
        if (this.mExpiryValid) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (this.mCompositeValid) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        parcel.writeInt(i3);
        if (this.mIdValid) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        parcel.writeInt(i4);
        if (!this.mPersNumValid) {
            i5 = 0;
        }
        parcel.writeInt(i5);
    }
}

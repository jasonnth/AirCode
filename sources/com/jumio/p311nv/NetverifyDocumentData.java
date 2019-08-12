package com.jumio.p311nv;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.jumio.commons.utils.StringUtil;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.enums.EPassportStatus;
import com.jumio.p311nv.enums.NVExtractionMethod;
import com.jumio.p311nv.enums.NVGender;
import java.io.Serializable;
import java.util.Date;

/* renamed from: com.jumio.nv.NetverifyDocumentData */
public class NetverifyDocumentData implements Parcelable, Serializable {
    public static final Creator<NetverifyDocumentData> CREATOR = new Creator<NetverifyDocumentData>() {
        /* renamed from: a */
        public NetverifyDocumentData createFromParcel(Parcel parcel) {
            return new NetverifyDocumentData(parcel);
        }

        /* renamed from: a */
        public NetverifyDocumentData[] newArray(int i) {
            return new NetverifyDocumentData[i];
        }
    };

    /* renamed from: a */
    private String f3247a = null;

    /* renamed from: b */
    private NVDocumentType f3248b = null;

    /* renamed from: c */
    private String f3249c = null;

    /* renamed from: d */
    private String f3250d = null;

    /* renamed from: e */
    private Date f3251e = null;

    /* renamed from: f */
    private Date f3252f = null;

    /* renamed from: g */
    private String f3253g = null;

    /* renamed from: h */
    private String f3254h = null;

    /* renamed from: i */
    private String f3255i = null;

    /* renamed from: j */
    private String f3256j = null;

    /* renamed from: k */
    private Date f3257k = null;

    /* renamed from: l */
    private NVGender f3258l = null;

    /* renamed from: m */
    private String f3259m = null;
    protected NetverifyMrzData mrzData = null;

    /* renamed from: n */
    private String f3260n = null;

    /* renamed from: o */
    private String f3261o = null;

    /* renamed from: p */
    private String f3262p = null;

    /* renamed from: q */
    private String f3263q = null;

    /* renamed from: r */
    private String f3264r = null;

    /* renamed from: s */
    private String f3265s = null;

    /* renamed from: t */
    private String f3266t = null;

    /* renamed from: u */
    private EPassportStatus f3267u = EPassportStatus.NOT_AVAILABLE;

    /* renamed from: v */
    private NVExtractionMethod f3268v = NVExtractionMethod.NONE;

    public NetverifyDocumentData() {
    }

    public NetverifyDocumentData(Parcel parcel) {
        NVGender nVGender = null;
        this.f3247a = m1940a(parcel.readString());
        String readString = parcel.readString();
        this.f3248b = readString.length() == 0 ? null : NVDocumentType.valueOf(readString);
        this.f3249c = m1940a(parcel.readString());
        this.f3250d = m1940a(parcel.readString());
        long readLong = parcel.readLong();
        this.f3251e = readLong == 0 ? null : new Date(readLong);
        long readLong2 = parcel.readLong();
        this.f3252f = readLong2 == 0 ? null : new Date(readLong2);
        this.f3253g = m1940a(parcel.readString());
        this.f3254h = m1940a(parcel.readString());
        this.f3255i = m1940a(parcel.readString());
        this.f3256j = m1940a(parcel.readString());
        long readLong3 = parcel.readLong();
        this.f3257k = readLong3 == 0 ? null : new Date(readLong3);
        String readString2 = parcel.readString();
        if (readString2.length() != 0) {
            nVGender = NVGender.valueOf(readString2);
        }
        this.f3258l = nVGender;
        this.f3259m = m1940a(parcel.readString());
        this.f3260n = m1940a(parcel.readString());
        this.f3261o = m1940a(parcel.readString());
        this.f3262p = m1940a(parcel.readString());
        this.f3263q = m1940a(parcel.readString());
        this.f3264r = m1940a(parcel.readString());
        this.f3265s = m1940a(parcel.readString());
        this.mrzData = (NetverifyMrzData) parcel.readParcelable(getClass().getClassLoader());
        this.f3266t = parcel.readString();
        try {
            this.f3267u = EPassportStatus.valueOf(parcel.readString());
        } catch (Exception e) {
            this.f3267u = EPassportStatus.NOT_AVAILABLE;
        }
        try {
            this.f3268v = NVExtractionMethod.valueOf(parcel.readString());
        } catch (Exception e2) {
            this.f3268v = NVExtractionMethod.NONE;
        }
    }

    public String getPlaceOfBirth() {
        return this.f3266t;
    }

    public void setPlaceOfBirth(String str) {
        this.f3266t = str;
    }

    public NetverifyDocumentData copy() {
        NetverifyDocumentData netverifyDocumentData = new NetverifyDocumentData();
        netverifyDocumentData.f3247a = this.f3247a;
        netverifyDocumentData.f3248b = this.f3248b;
        netverifyDocumentData.f3249c = this.f3249c;
        netverifyDocumentData.f3251e = this.f3251e;
        netverifyDocumentData.f3252f = this.f3252f;
        netverifyDocumentData.f3253g = this.f3253g;
        netverifyDocumentData.f3254h = this.f3254h;
        netverifyDocumentData.f3255i = this.f3255i;
        netverifyDocumentData.f3256j = this.f3256j;
        netverifyDocumentData.f3257k = this.f3257k;
        netverifyDocumentData.f3258l = this.f3258l;
        netverifyDocumentData.f3259m = this.f3259m;
        netverifyDocumentData.f3260n = this.f3260n;
        netverifyDocumentData.f3261o = this.f3261o;
        netverifyDocumentData.f3262p = this.f3262p;
        netverifyDocumentData.f3263q = this.f3263q;
        netverifyDocumentData.f3264r = this.f3264r;
        netverifyDocumentData.f3265s = this.f3265s;
        netverifyDocumentData.mrzData = this.mrzData;
        netverifyDocumentData.f3266t = this.f3266t;
        netverifyDocumentData.f3267u = this.f3267u;
        netverifyDocumentData.f3268v = this.f3268v;
        return netverifyDocumentData;
    }

    /* renamed from: a */
    private String m1940a(String str) {
        if (str.length() == 0) {
            return null;
        }
        return str;
    }

    public String getSelectedCountry() {
        return this.f3247a;
    }

    public void setSelectedCountry(String str) {
        this.f3247a = StringUtil.trim(str, 3);
    }

    public NVDocumentType getSelectedDocumentType() {
        return this.f3248b;
    }

    public void setSelectedDocumentType(NVDocumentType nVDocumentType) {
        this.f3248b = nVDocumentType;
    }

    public String getIdNumber() {
        return this.f3249c;
    }

    public void setIdNumber(String str) {
        this.f3249c = StringUtil.trim(str, 100);
    }

    public String getPersonalNumber() {
        return this.f3250d;
    }

    public void setPersonalNumber(String str) {
        this.f3250d = StringUtil.trim(str, 14, "^[A-Z0-9]*$");
    }

    public Date getIssuingDate() {
        return this.f3251e;
    }

    public void setIssuingDate(Date date) {
        this.f3251e = date;
    }

    public Date getExpiryDate() {
        return this.f3252f;
    }

    public void setExpiryDate(Date date) {
        this.f3252f = date;
    }

    public String getIssuingCountry() {
        return this.f3253g;
    }

    public void setIssuingCountry(String str) {
        this.f3253g = StringUtil.trim(str, 3, "[A-Z]{3}");
        if (!m1941b(str)) {
            this.f3253g = null;
        }
    }

    public String getLastName() {
        return this.f3254h;
    }

    public void setLastName(String str) {
        this.f3254h = StringUtil.trim(str, 100);
    }

    public String getFirstName() {
        return this.f3255i;
    }

    public void setFirstName(String str) {
        this.f3255i = StringUtil.trim(str, 100);
    }

    public String getMiddleName() {
        return this.f3256j;
    }

    public void setMiddleName(String str) {
        this.f3256j = StringUtil.trim(str, 100);
    }

    public Date getDob() {
        return this.f3257k;
    }

    public void setDob(Date date) {
        this.f3257k = date;
    }

    public NVGender getGender() {
        return this.f3258l;
    }

    public void setGender(NVGender nVGender) {
        this.f3258l = nVGender;
    }

    public String getOriginatingCountry() {
        return this.f3259m;
    }

    public void setOriginatingCountry(String str) {
        this.f3259m = StringUtil.trim(str, 3, "[A-Z]{3}");
        if (!m1941b(str)) {
            this.f3259m = null;
        }
    }

    public String getAddressLine() {
        return this.f3260n;
    }

    public void setAddressLine(String str) {
        this.f3260n = StringUtil.trim(str, 255);
    }

    public String getCity() {
        return this.f3261o;
    }

    public void setCity(String str) {
        this.f3261o = StringUtil.trim(str, 64);
    }

    public String getSubdivision() {
        return this.f3262p;
    }

    public void setSubdivision(String str) {
        this.f3262p = StringUtil.trim(str, 3, "[A-Z]{2,3}");
    }

    public String getPostCode() {
        return this.f3263q;
    }

    public void setPostCode(String str) {
        this.f3263q = StringUtil.trim(str, 15);
    }

    public String getOptionalData1() {
        return this.f3264r;
    }

    public void setOptionalData1(String str) {
        this.f3264r = StringUtil.trim(str, 50, "^[A-Z0-9]*$");
    }

    public String getOptionalData2() {
        return this.f3265s;
    }

    public void setOptionalData2(String str) {
        this.f3265s = StringUtil.trim(str, 50, "^[A-Z0-9]*$");
    }

    /* renamed from: b */
    private boolean m1941b(String str) {
        if (str == null || str.length() == 0 || new Country(str).getName().equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        long j = 0;
        parcel.writeString(this.f3247a == null ? "" : this.f3247a);
        parcel.writeString(this.f3248b == null ? "" : this.f3248b.name());
        parcel.writeString(this.f3249c == null ? "" : this.f3249c);
        parcel.writeString(this.f3250d == null ? "" : this.f3250d);
        parcel.writeLong(this.f3251e == null ? 0 : this.f3251e.getTime());
        parcel.writeLong(this.f3252f == null ? 0 : this.f3252f.getTime());
        parcel.writeString(this.f3253g == null ? "" : this.f3253g);
        parcel.writeString(this.f3254h == null ? "" : this.f3254h);
        parcel.writeString(this.f3255i == null ? "" : this.f3255i);
        parcel.writeString(this.f3256j == null ? "" : this.f3256j);
        if (this.f3257k != null) {
            j = this.f3257k.getTime();
        }
        parcel.writeLong(j);
        parcel.writeString(this.f3258l == null ? "" : this.f3258l.name());
        parcel.writeString(this.f3259m == null ? "" : this.f3259m);
        parcel.writeString(this.f3260n == null ? "" : this.f3260n);
        parcel.writeString(this.f3261o == null ? "" : this.f3261o);
        parcel.writeString(this.f3262p == null ? "" : this.f3262p);
        parcel.writeString(this.f3263q == null ? "" : this.f3263q);
        parcel.writeString(this.f3264r == null ? "" : this.f3264r);
        parcel.writeString(this.f3265s == null ? "" : this.f3265s);
        parcel.writeParcelable(this.mrzData, 0);
        parcel.writeString(this.f3266t);
        parcel.writeString(this.f3267u.toString());
        parcel.writeString(this.f3268v.name());
    }

    public NetverifyMrzData getMrzData() {
        return this.mrzData;
    }

    public void setMrzData(NetverifyMrzData netverifyMrzData) {
        this.mrzData = netverifyMrzData;
    }

    public EPassportStatus getEPassportStatus() {
        return this.f3267u;
    }

    public void setEPassportStatus(EPassportStatus ePassportStatus) {
        this.f3267u = ePassportStatus;
    }

    public NVExtractionMethod getExtractionMethod() {
        return this.f3268v;
    }

    public void setExtractionMethod(NVExtractionMethod nVExtractionMethod) {
        this.f3268v = nVExtractionMethod;
    }
}

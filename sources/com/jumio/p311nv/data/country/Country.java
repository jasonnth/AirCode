package com.jumio.p311nv.data.country;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.jumio.p311nv.IsoCountryConverter;
import com.jumio.p311nv.data.document.DocumentType;
import java.io.Serializable;
import java.text.CollationKey;
import java.text.Collator;
import java.util.Locale;
import jumio.p317nv.core.C4931k;

/* renamed from: com.jumio.nv.data.country.Country */
public class Country implements Parcelable, Serializable, Comparable<Country> {
    public static final Creator<Country> CREATOR = new Creator<Country>() {
        /* renamed from: a */
        public Country createFromParcel(Parcel parcel) {
            return new Country(parcel);
        }

        /* renamed from: a */
        public Country[] newArray(int i) {
            return new Country[i];
        }
    };

    /* renamed from: a */
    private String f3341a = "";

    /* renamed from: b */
    private String f3342b = "";

    /* renamed from: c */
    private C4931k f3343c = C4931k.RAW;

    public Country(String str) {
        String str2;
        if (str.length() == 3) {
            str2 = IsoCountryConverter.convertToAlpha2(str);
        } else {
            str2 = str;
        }
        if (str2 == null) {
            str2 = "";
        }
        m1972a(str, new Locale("", str2).getDisplayCountry());
    }

    public Country(String str, String str2) {
        m1972a(str, str2);
    }

    public Country(Parcel parcel) {
        this.f3341a = parcel.readString();
        this.f3342b = parcel.readString();
        this.f3343c = C4931k.valueOf(parcel.readString());
    }

    /* renamed from: a */
    private void m1972a(String str, String str2) {
        this.f3341a = str;
        if (str2.equals("")) {
            this.f3342b = str;
        } else {
            this.f3342b = str2;
        }
    }

    public CollationKey getCollationKey() {
        return Collator.getInstance().getCollationKey(this.f3342b);
    }

    public String getIsoCode() {
        return this.f3341a;
    }

    public void setIsoCode(String str) {
        this.f3341a = str;
    }

    public String getName() {
        return this.f3342b;
    }

    public void setName(String str) {
        this.f3342b = str;
    }

    public C4931k getAddressFormat() {
        return this.f3343c;
    }

    public void setAddressFormat(C4931k kVar) {
        this.f3343c = kVar;
    }

    public boolean prepareTemplateMatcher(Context context, DocumentType documentType) {
        return false;
    }

    public int compareTo(Country country) {
        return Collator.getInstance().getCollationKey(this.f3342b).compareTo(country.getCollationKey());
    }

    public boolean canUseTemplateMatcher(Context context, DocumentType documentType) {
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f3342b == null ? 0 : this.f3342b.hashCode()) + 31) * 31;
        if (this.f3341a != null) {
            i = this.f3341a.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Country)) {
            return false;
        }
        Country country = (Country) obj;
        if (!country.f3342b.equals(this.f3342b) || !country.getIsoCode().equals(this.f3341a)) {
            return false;
        }
        return true;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3341a);
        parcel.writeString(this.f3342b);
        parcel.writeString(this.f3343c.name());
    }

    public String toString() {
        return String.format("%s (%s)", new Object[]{this.f3342b, this.f3341a});
    }
}

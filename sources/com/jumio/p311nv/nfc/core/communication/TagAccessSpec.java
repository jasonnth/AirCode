package com.jumio.p311nv.nfc.core.communication;

import java.io.Serializable;
import java.util.Date;

/* renamed from: com.jumio.nv.nfc.core.communication.TagAccessSpec */
public class TagAccessSpec implements Serializable {

    /* renamed from: a */
    private String f3448a;

    /* renamed from: b */
    private Date f3449b;

    /* renamed from: c */
    private Date f3450c;

    /* renamed from: d */
    private String f3451d;

    /* renamed from: e */
    private boolean f3452e;

    public TagAccessSpec(String str, Date date, Date date2, String str2, boolean z) {
        this.f3448a = str;
        this.f3449b = date;
        this.f3450c = date2;
        this.f3451d = str2;
        this.f3452e = z;
    }

    public TagAccessSpec(String str, Date date, Date date2, String str2) {
        this(str, date, date2, str2, false);
    }

    public TagAccessSpec() {
    }

    public String getPassportNumber() {
        return this.f3448a;
    }

    public Date getDateOfBirth() {
        return this.f3449b;
    }

    public Date getDateOfExpiry() {
        return this.f3450c;
    }

    public String getCountryIso3() {
        return this.f3451d;
    }

    public boolean shouldDownloadFaceImage() {
        return this.f3452e;
    }

    public void setDateOfExpiry(Date date) {
        this.f3450c = date;
    }

    public void setPassportNumber(String str) {
        this.f3448a = str;
    }

    public void setDateOfBirth(Date date) {
        this.f3449b = date;
    }

    public void setCountryIso3(String str) {
        this.f3451d = str;
    }

    public void setShouldDownloadFaceimage(boolean z) {
        this.f3452e = z;
    }
}

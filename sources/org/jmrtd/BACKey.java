package org.jmrtd;

import com.jumio.analytics.MobileEvents;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BACKey implements BACKeySpec {
    static final /* synthetic */ boolean $assertionsDisabled = (!BACKey.class.desiredAssertionStatus());
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyMMdd");
    private static final long serialVersionUID = -1059774581180524710L;
    private String dateOfBirth;
    private String dateOfExpiry;
    private String documentNumber;

    protected BACKey() {
    }

    public BACKey(String str, String str2, String str3) {
        if (str == null) {
            throw new IllegalArgumentException("Illegal document number");
        } else if (str2 == null || str2.length() != 6) {
            throw new IllegalArgumentException("Illegal date: " + str2);
        } else if (str3 == null || str3.length() != 6) {
            throw new IllegalArgumentException("Illegal date: " + str3);
        } else {
            while (str.length() < 9) {
                str = str + "<";
            }
            this.documentNumber = str.trim();
            this.dateOfBirth = str2;
            this.dateOfExpiry = str3;
        }
    }

    public BACKey(String str, Date date, Date date2) {
        this(str, toString(date), toString(date2));
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getDateOfExpiry() {
        return this.dateOfExpiry;
    }

    public String toString() {
        if (!$assertionsDisabled && (this.dateOfBirth == null || this.dateOfBirth.length() != 6)) {
            throw new AssertionError();
        } else if ($assertionsDisabled || (this.dateOfExpiry != null && this.dateOfExpiry.length() == 6)) {
            return this.documentNumber + ", " + this.dateOfBirth + ", " + this.dateOfExpiry;
        } else {
            throw new AssertionError();
        }
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.dateOfBirth == null ? 0 : this.dateOfBirth.hashCode()) + (((this.documentNumber == null ? 0 : this.documentNumber.hashCode()) + MobileEvents.EVENTTYPE_EXCEPTION) * 61)) * 61;
        if (this.dateOfExpiry != null) {
            i = this.dateOfExpiry.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        BACKey bACKey = (BACKey) obj;
        if (!this.documentNumber.equals(bACKey.documentNumber) || !this.dateOfBirth.equals(bACKey.dateOfBirth) || !this.dateOfExpiry.equals(bACKey.dateOfExpiry)) {
            z = false;
        }
        return z;
    }

    public String getAlgorithm() {
        return "BAC";
    }

    public byte[] getEncoded() {
        return null;
    }

    public String getFormat() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void setDocumentNumber(String str) {
        this.documentNumber = str;
    }

    /* access modifiers changed from: protected */
    public void setDateOfBirth(String str) {
        this.dateOfBirth = str;
    }

    /* access modifiers changed from: protected */
    public void setDateOfExpiry(String str) {
        this.dateOfExpiry = str;
    }

    private static synchronized String toString(Date date) {
        String format;
        synchronized (BACKey.class) {
            format = SDF.format(date);
        }
        return format;
    }
}

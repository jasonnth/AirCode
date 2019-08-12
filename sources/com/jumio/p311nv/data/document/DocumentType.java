package com.jumio.p311nv.data.document;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import java.io.Serializable;

/* renamed from: com.jumio.nv.data.document.DocumentType */
public class DocumentType implements Parcelable, Serializable, Comparable<DocumentType> {
    public static final String BARCODE_FORMAT_PDF417 = "PDF417";
    public static final String BARCODE_SIDE_BACK = "BACK";
    public static final String BARCODE_SIDE_FRONT = "FRONT";
    public static final Creator<DocumentType> CREATOR = new Creator<DocumentType>() {
        /* renamed from: a */
        public DocumentType createFromParcel(Parcel parcel) {
            return new DocumentType(parcel);
        }

        /* renamed from: a */
        public DocumentType[] newArray(int i) {
            return new DocumentType[i];
        }
    };
    public static final String MRZ_FORMAT_CNIS = "CNIS";
    public static final String MRZ_FORMAT_MRP = "MRP";
    public static final String MRZ_FORMAT_MRV = "MRV";
    public static final String MRZ_FORMAT_MRV_A = "MRV_A";
    public static final String MRZ_FORMAT_MRV_B = "MRV_B";
    public static final String MRZ_FORMAT_TD1 = "TD1";
    public static final String MRZ_FORMAT_TD2 = "TD2";
    public static final String MRZ_SIDE_BACK = "BACK";
    public static final String MRZ_SIDE_FRONT = "FRONT";
    public static final String THIRD_PARTY_OCR_FORMAT_CSSN = "CSSN";

    /* renamed from: a */
    private final boolean f3344a;

    /* renamed from: b */
    private NVDocumentType f3345b;

    /* renamed from: c */
    private int f3346c = 1;

    /* renamed from: d */
    private boolean f3347d;

    /* renamed from: e */
    private DocumentScanMode f3348e = null;

    /* renamed from: f */
    private ScanSide f3349f = null;

    /* renamed from: g */
    private int f3350g = 1;

    /* renamed from: h */
    private DocumentScanMode f3351h = null;

    /* renamed from: i */
    private ScanSide f3352i = null;

    /* renamed from: j */
    private boolean f3353j = false;

    /* renamed from: k */
    private boolean f3354k = false;

    /* renamed from: l */
    private NVDocumentVariant f3355l = null;

    public DocumentType(String str, int i, String str2, String str3, String str4, String str5, String str6, boolean z, int i2) {
        this.f3345b = NVDocumentType.fromString(str);
        this.f3346c = i;
        if (this.f3345b == NVDocumentType.PASSPORT) {
            this.f3348e = DocumentScanMode.MRP;
        } else if (this.f3345b == NVDocumentType.VISA) {
            this.f3348e = DocumentScanMode.MRV;
        } else {
            this.f3348e = DocumentScanMode.LINEFINDER;
        }
        this.f3349f = ScanSide.FRONT;
        if (str2.length() != 0) {
            if (MRZ_FORMAT_MRP.equals(str2)) {
                this.f3348e = DocumentScanMode.MRP;
            } else if (MRZ_FORMAT_MRV.equals(str2) || MRZ_FORMAT_MRV_A.equals(str2) || MRZ_FORMAT_MRV_B.equals(str2)) {
                this.f3348e = DocumentScanMode.MRV;
            } else if (MRZ_FORMAT_TD1.equals(str2)) {
                this.f3348e = DocumentScanMode.TD1;
            } else if (MRZ_FORMAT_TD2.equals(str2)) {
                this.f3348e = DocumentScanMode.TD2;
            } else if (MRZ_FORMAT_CNIS.equals(str2)) {
                this.f3348e = DocumentScanMode.CNIS;
            }
            if ("FRONT".equals(str3)) {
                this.f3349f = ScanSide.FRONT;
            } else if ("BACK".equals(str3)) {
                this.f3349f = ScanSide.BACK;
            }
            this.f3347d = true;
        } else if (str4.length() != 0) {
            if ("PDF417".equals(str4)) {
                this.f3348e = DocumentScanMode.PDF417;
            }
            if ("FRONT".equals(str5)) {
                this.f3349f = ScanSide.FRONT;
            } else if ("BACK".equals(str5)) {
                this.f3349f = ScanSide.BACK;
            }
            this.f3347d = true;
        }
        if (str6.length() != 0) {
            if (THIRD_PARTY_OCR_FORMAT_CSSN.equals(str6)) {
                if (this.f3348e == DocumentScanMode.LINEFINDER && this.f3349f == ScanSide.FRONT) {
                    this.f3348e = DocumentScanMode.CSSN;
                    this.f3349f = ScanSide.FRONT;
                    this.f3353j = false;
                } else {
                    this.f3353j = true;
                }
                this.f3347d = true;
            }
            this.f3344a = true;
        } else {
            this.f3344a = false;
        }
        this.f3354k = z;
        this.f3352i = ScanSide.FRONT;
        this.f3351h = DocumentScanMode.MANUAL;
        this.f3350g = i2;
    }

    public DocumentType(Parcel parcel) {
        boolean z;
        NVDocumentVariant nVDocumentVariant;
        boolean z2 = true;
        this.f3345b = NVDocumentType.valueOf(parcel.readString());
        this.f3346c = parcel.readInt();
        this.f3348e = DocumentScanMode.valueOf(parcel.readString());
        this.f3349f = ScanSide.valueOf(parcel.readString());
        this.f3350g = parcel.readInt();
        this.f3351h = DocumentScanMode.valueOf(parcel.readString());
        this.f3352i = ScanSide.valueOf(parcel.readString());
        this.f3353j = parcel.readInt() == 1;
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.f3354k = z;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        this.f3344a = z2;
        String readString = parcel.readString();
        if (readString.length() != 0) {
            nVDocumentVariant = NVDocumentVariant.valueOf(readString);
        } else {
            nVDocumentVariant = null;
        }
        this.f3355l = nVDocumentVariant;
    }

    public DocumentType(DocumentType documentType) {
        this.f3345b = documentType.f3345b;
        this.f3346c = documentType.f3346c;
        this.f3348e = documentType.f3348e;
        this.f3349f = documentType.f3349f;
        this.f3350g = documentType.f3350g;
        this.f3351h = documentType.f3351h;
        this.f3352i = documentType.f3352i;
        this.f3353j = documentType.f3353j;
        this.f3354k = documentType.f3354k;
        this.f3344a = documentType.f3344a;
        this.f3355l = documentType.f3355l;
    }

    public NVDocumentType getId() {
        return this.f3345b;
    }

    public void setId(NVDocumentType nVDocumentType) {
        this.f3345b = nVDocumentType;
    }

    public int getParts() {
        return NVDocumentVariant.PAPER.equals(this.f3355l) ? this.f3350g : this.f3346c;
    }

    public void setParts(int i) {
        this.f3346c = i;
    }

    public DocumentScanMode getDocumentScanMode() {
        return NVDocumentVariant.PAPER.equals(this.f3355l) ? this.f3351h : this.f3348e;
    }

    public void setDocumentScanMode(DocumentScanMode documentScanMode) {
        this.f3348e = documentScanMode;
        if (documentScanMode == DocumentScanMode.TEMPLATEMATCHER) {
            this.f3353j = true;
        }
    }

    public ScanSide getDocumentScanSide() {
        return NVDocumentVariant.PAPER.equals(this.f3355l) ? this.f3352i : this.f3349f;
    }

    public void setDocumentScanSide(ScanSide scanSide) {
        this.f3349f = scanSide;
    }

    public boolean hasFallbackScan() {
        return this.f3353j || this.f3348e.equals(DocumentScanMode.TEMPLATEMATCHER);
    }

    public void setFallbackScan(boolean z) {
        this.f3353j = z;
    }

    public boolean isThirdPartyOcrDefined() {
        return this.f3344a;
    }

    public boolean isDocumentVariantAccepted(NVDocumentVariant nVDocumentVariant) {
        return (this.f3354k && nVDocumentVariant == NVDocumentVariant.PAPER) || nVDocumentVariant == NVDocumentVariant.PLASTIC || nVDocumentVariant == null;
    }

    public void setPaperParts(int i) {
        this.f3350g = i;
    }

    public void setPaperScanMode(DocumentScanMode documentScanMode) {
        this.f3351h = documentScanMode;
    }

    public void setPaperScanSide(ScanSide scanSide) {
        this.f3352i = scanSide;
    }

    public String toString() {
        return this.f3345b.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3;
        int i4 = 1;
        parcel.writeString(this.f3345b.name());
        parcel.writeInt(this.f3346c);
        parcel.writeString(this.f3348e.name());
        parcel.writeString(this.f3349f.name());
        parcel.writeInt(this.f3350g);
        parcel.writeString(this.f3351h.name());
        parcel.writeString(this.f3352i.name());
        if (this.f3353j) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (this.f3354k) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        parcel.writeInt(i3);
        if (!this.f3344a) {
            i4 = 0;
        }
        parcel.writeInt(i4);
        parcel.writeString(this.f3355l == null ? "" : this.f3355l.name());
    }

    public int compareTo(DocumentType documentType) {
        if (this.f3345b == NVDocumentType.PASSPORT && documentType.getId() != NVDocumentType.PASSPORT) {
            return -1;
        }
        if (this.f3345b == NVDocumentType.DRIVER_LICENSE && documentType.getId() == NVDocumentType.PASSPORT) {
            return 1;
        }
        if (this.f3345b == NVDocumentType.DRIVER_LICENSE && documentType.getId() != NVDocumentType.DRIVER_LICENSE) {
            return -1;
        }
        if (this.f3345b == NVDocumentType.IDENTITY_CARD && documentType.getId() == NVDocumentType.PASSPORT) {
            return 1;
        }
        if (this.f3345b == NVDocumentType.IDENTITY_CARD && documentType.getId() == NVDocumentType.DRIVER_LICENSE) {
            return 1;
        }
        if ((this.f3345b == NVDocumentType.IDENTITY_CARD && documentType.getId() == NVDocumentType.VISA) || this.f3345b != NVDocumentType.VISA || documentType.getId() == NVDocumentType.VISA) {
            return -1;
        }
        return 1;
    }

    public boolean equals(Object obj) {
        int i = -1;
        if (obj instanceof DocumentType) {
            i = compareTo((DocumentType) obj);
        }
        return i == 0;
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public boolean hasExtractionMethod() {
        return this.f3347d;
    }

    public boolean hasPaperVariant() {
        return this.f3354k;
    }

    public void setPaperVariant(boolean z) {
        this.f3354k = z;
    }

    public NVDocumentVariant getDocumentVariant() {
        return this.f3355l;
    }

    public void setDocumentVariant(NVDocumentVariant nVDocumentVariant) {
        if (isDocumentVariantAccepted(nVDocumentVariant)) {
            this.f3355l = nVDocumentVariant;
        }
    }
}

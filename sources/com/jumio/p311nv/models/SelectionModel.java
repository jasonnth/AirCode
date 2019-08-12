package com.jumio.p311nv.models;

import android.content.Context;
import com.jumio.commons.PersistWith;
import com.jumio.commons.log.Log;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.p311nv.NetverifyDocumentData;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.DocumentType;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.data.document.NVDocumentVariant;
import com.jumio.persistence.DataAccess;
import java.util.ArrayList;

@PersistWith("SelectionModel")
/* renamed from: com.jumio.nv.models.SelectionModel */
public class SelectionModel implements StaticModel {

    /* renamed from: a */
    private Country f3413a;

    /* renamed from: b */
    private DocumentType f3414b;

    /* renamed from: c */
    private NVDocumentVariant f3415c = NVDocumentVariant.PLASTIC;

    /* renamed from: d */
    private boolean f3416d = false;

    /* renamed from: e */
    private UploadDataModel f3417e;

    public Country getSelectedCountry() {
        return this.f3413a;
    }

    public void setSelectedCountry(Country country) {
        this.f3413a = country;
    }

    public void buildUploadModel(Context context) {
        int i = 0;
        if (getSelectedDoctype() != null && getSelectedCountry() != null) {
            Log.m1919i("SelectionModel", "creating upload model");
            this.f3417e = new UploadDataModel();
            if (!isVerificationRequired()) {
                ScanSide documentScanSide = getSelectedDoctype().getDocumentScanSide();
                this.f3417e.add(new NVScanPartModel(documentScanSide, m1987a(documentScanSide), 0));
                return;
            }
            ScanSide[] a = m1988a(context);
            int length = a.length;
            int i2 = 0;
            while (i < length) {
                ScanSide scanSide = a[i];
                int i3 = i2 + 1;
                this.f3417e.add(new NVScanPartModel(scanSide, m1987a(scanSide), i2));
                i++;
                i2 = i3;
            }
        }
    }

    /* renamed from: a */
    private ScanSide[] m1988a(Context context) {
        int parts = (getSelectedDoctype().getDocumentScanSide().getPartNumber() >= getSelectedDoctype().getParts() ? 1 : 0) + getSelectedDoctype().getParts();
        ArrayList arrayList = new ArrayList();
        if (parts >= 1) {
            arrayList.add(ScanSide.FRONT);
        }
        if (parts >= 2) {
            arrayList.add(ScanSide.BACK);
        }
        MerchantSettingsModel merchantSettingsModel = (MerchantSettingsModel) DataAccess.load(context, MerchantSettingsModel.class);
        if (merchantSettingsModel != null && merchantSettingsModel.isFaceMatchEnabled()) {
            arrayList.add(ScanSide.FACE);
        }
        return (ScanSide[]) arrayList.toArray(new ScanSide[arrayList.size()]);
    }

    /* renamed from: a */
    private DocumentScanMode m1987a(ScanSide scanSide) {
        if (getSelectedDoctype().getDocumentScanSide() == scanSide) {
            return getSelectedDoctype().getDocumentScanMode();
        }
        if (ScanSide.FACE == scanSide) {
            return DocumentScanMode.FACE_MANUAL;
        }
        if (getSelectedDoctype().getDocumentVariant() != NVDocumentVariant.PAPER) {
            return DocumentScanMode.LINEFINDER;
        }
        if (!ServerSettingsModel.GERMAN_ISO3.equals(getSelectedCountry().getIsoCode()) || getSelectedDoctype().getId() != NVDocumentType.IDENTITY_CARD) {
            return DocumentScanMode.MANUAL;
        }
        return DocumentScanMode.LINEFINDER;
    }

    public DocumentType getSelectedDoctype() {
        return this.f3414b;
    }

    public void setSelectedDoctype(DocumentType documentType) {
        this.f3414b = documentType;
    }

    public NVDocumentVariant getSelectedVariant() {
        return this.f3415c;
    }

    public void setSelectedVariant(NVDocumentVariant nVDocumentVariant) {
        this.f3415c = nVDocumentVariant;
        if (this.f3414b != null) {
            this.f3414b.setDocumentVariant(nVDocumentVariant);
        }
    }

    public boolean isVerificationRequired() {
        return this.f3416d;
    }

    public void setVerificationRequired(boolean z) {
        this.f3416d = z;
    }

    public UploadDataModel getUploadModel() {
        return this.f3417e;
    }

    public void setUploadModel(UploadDataModel uploadDataModel) {
        this.f3417e = uploadDataModel;
    }

    public void populateData(NetverifyDocumentData netverifyDocumentData) {
        netverifyDocumentData.setSelectedCountry(this.f3413a.getIsoCode());
        netverifyDocumentData.setSelectedDocumentType(this.f3414b.getId());
    }
}

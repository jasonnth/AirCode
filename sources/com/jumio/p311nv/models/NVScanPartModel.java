package com.jumio.p311nv.models;

import com.jumio.commons.PersistWith;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.sdk.models.BaseScanPartModel;

@PersistWith("ScanPartModel")
/* renamed from: com.jumio.nv.models.NVScanPartModel */
public class NVScanPartModel extends BaseScanPartModel {

    /* renamed from: a */
    private DocumentDataModel f3410a;

    /* renamed from: b */
    private int f3411b;

    public NVScanPartModel(ScanSide scanSide, DocumentScanMode documentScanMode, int i) {
        super(scanSide, documentScanMode);
        this.f3411b = i;
    }

    public DocumentDataModel getDocumentInfo() {
        return this.f3410a;
    }

    public void setDocumentInfo(DocumentDataModel documentDataModel) {
        this.f3410a = documentDataModel;
    }

    public int getPartIndex() {
        return this.f3411b;
    }
}

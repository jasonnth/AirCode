package com.jumio.p311nv.models;

import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.p311nv.extraction.NfcController;

/* renamed from: com.jumio.nv.models.NfcScanPartModel */
public class NfcScanPartModel extends NVScanPartModel {

    /* renamed from: a */
    private NfcController f3412a;

    public NfcScanPartModel(NfcController nfcController) {
        super(ScanSide.FRONT, DocumentScanMode.NFC, 4);
        this.f3412a = nfcController;
    }

    public NfcController getNfcController() {
        return this.f3412a;
    }
}

package com.jumio.p311nv.view.interactors;

import android.os.Bundle;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.data.document.NVDocumentVariant;
import com.jumio.p311nv.extraction.NfcController;
import com.jumio.sdk.models.CredentialsModel;
import com.jumio.sdk.view.interactors.BaseScanView;

/* renamed from: com.jumio.nv.view.interactors.NVScanView */
public interface NVScanView extends BaseScanView {

    /* renamed from: com.jumio.nv.view.interactors.NVScanView$GuiState */
    public enum GuiState {
        HELP,
        CONFIRMATION,
        LOADING,
        SCAN
    }

    /* renamed from: com.jumio.nv.view.interactors.NVScanView$NVHelpConfiguration */
    public static class NVHelpConfiguration {
        public NVDocumentType documentType;
        public NVDocumentVariant documentVariant;
        public boolean isUSDLFallback;
        public int part;
        public DocumentScanMode scanMode;
        public boolean showFallback;
        public ScanSide side;
        public int totalParts;
    }

    void extractionStarted();

    CredentialsModel getCredentialsModel();

    NfcController getNfcController();

    void hideHelp();

    void partComplete();

    void scansComplete();

    void showConfirmation(String str, boolean z);

    void showHelp(NVHelpConfiguration nVHelpConfiguration, boolean z);

    void showLoading();

    void showNFC(Bundle bundle);

    void showSnackbar(NVHelpConfiguration nVHelpConfiguration);
}

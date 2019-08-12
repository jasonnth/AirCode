package com.jumio.p311nv.view.interactors;

import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.DocumentType;
import com.jumio.p311nv.data.document.NVDocumentVariant;
import com.jumio.sdk.models.CredentialsModel;
import com.jumio.sdk.view.InteractibleView;
import java.util.List;

/* renamed from: com.jumio.nv.view.interactors.SelectionView */
public interface SelectionView extends InteractibleView {
    CredentialsModel getCredentialsModel();

    void hideLoading(boolean z);

    void jumpToScanFragment(boolean z);

    void onCountriesReceived(List<Country> list, Country country, boolean z);

    void onDocumentTypesReceived(List<DocumentType> list, NVDocumentVariant nVDocumentVariant);
}

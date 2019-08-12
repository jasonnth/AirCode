package com.jumio.p311nv.view.interactors;

import com.jumio.sdk.models.CredentialsModel;
import com.jumio.sdk.view.InteractibleView;

/* renamed from: com.jumio.nv.view.interactors.UploadView */
public interface UploadView extends InteractibleView {
    CredentialsModel getCredentialsModel();

    void uploadComplete();
}

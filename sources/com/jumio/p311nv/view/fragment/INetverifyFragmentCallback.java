package com.jumio.p311nv.view.fragment;

import com.jumio.commons.utils.DeviceRotationManager;
import com.jumio.p311nv.extraction.NfcController;
import com.jumio.sdk.view.fragment.IBaseFragmentCallback;

/* renamed from: com.jumio.nv.view.fragment.INetverifyFragmentCallback */
public interface INetverifyFragmentCallback extends IBaseFragmentCallback<INetverifyActivityCallback> {
    void animateActionbar(boolean z, boolean z2);

    NfcController getNfcController();

    DeviceRotationManager getRotationManager();
}

package com.google.android.exoplayer.drm;

import android.media.DeniedByServerException;
import android.media.MediaCryptoException;
import android.media.NotProvisionedException;
import android.media.ResourceBusyException;
import com.google.android.exoplayer.drm.ExoMediaCrypto;
import java.util.HashMap;
import java.util.UUID;

public interface ExoMediaDrm<T extends ExoMediaCrypto> {

    public interface KeyRequest {
    }

    public interface OnEventListener<T extends ExoMediaCrypto> {
        void onEvent(ExoMediaDrm<? extends T> exoMediaDrm, byte[] bArr, int i, int i2, byte[] bArr2);
    }

    public interface ProvisionRequest {
    }

    void closeSession(byte[] bArr);

    T createMediaCrypto(UUID uuid, byte[] bArr) throws MediaCryptoException;

    KeyRequest getKeyRequest(byte[] bArr, byte[] bArr2, String str, int i, HashMap<String, String> hashMap) throws NotProvisionedException;

    String getPropertyString(String str);

    ProvisionRequest getProvisionRequest();

    byte[] openSession() throws NotProvisionedException, ResourceBusyException;

    byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) throws NotProvisionedException, DeniedByServerException;

    void provideProvisionResponse(byte[] bArr) throws DeniedByServerException;

    void setOnEventListener(OnEventListener<? super T> onEventListener);
}

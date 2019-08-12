package com.mparticle;

public interface DeepLinkListener {
    void onError(DeepLinkError deepLinkError);

    void onResult(DeepLinkResult deepLinkResult);
}

package com.airbnb.jitney.event.logging.DeepLinkOperationType.p083v1;

/* renamed from: com.airbnb.jitney.event.logging.DeepLinkOperationType.v1.DeepLinkOperationType */
public enum C1984DeepLinkOperationType {
    OpenUrl(1),
    Success(2),
    Failure(3),
    Unsupported(4),
    ShowWebview(5);
    
    public final int value;

    private C1984DeepLinkOperationType(int value2) {
        this.value = value2;
    }
}

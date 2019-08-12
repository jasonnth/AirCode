package com.airbnb.jitney.event.logging.DeepLinkOriginType.p084v1;

/* renamed from: com.airbnb.jitney.event.logging.DeepLinkOriginType.v1.DeepLinkOriginType */
public enum C1985DeepLinkOriginType {
    Unknown(1),
    QuickAction(2),
    Branch(3),
    RemoteNotification(4),
    OpenUrl(5),
    Handoff(6),
    Guidebooks(7),
    Internal(8),
    Story(9);
    
    public final int value;

    private C1985DeepLinkOriginType(int value2) {
        this.value = value2;
    }
}

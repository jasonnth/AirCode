package com.airbnb.jitney.event.logging.IdentityFlowPagesType.p121v1;

/* renamed from: com.airbnb.jitney.event.logging.IdentityFlowPagesType.v1.IdentityFlowPagesType */
public enum C2232IdentityFlowPagesType {
    Unknown(0),
    ProvideIDIntro(1),
    AddPhoto(2),
    ProfilePhotoReview(3),
    GovernmentIDIntro(4),
    IDTypeSelection(5),
    IDCountrySelection(6),
    JumioIDScan(7),
    SelfieIntro(8),
    SelfieCamera(9),
    SelfieReview(10),
    FlowCompletion(11),
    IDNotListed(12);
    
    public final int value;

    private C2232IdentityFlowPagesType(int value2) {
        this.value = value2;
    }
}

package com.airbnb.android.core.security;

import com.threatmetrix.TrustDefender.EndNotifier;
import com.threatmetrix.TrustDefender.ProfilingResult;

final /* synthetic */ class ThreatMetrixClient$$Lambda$1 implements EndNotifier {
    private static final ThreatMetrixClient$$Lambda$1 instance = new ThreatMetrixClient$$Lambda$1();

    private ThreatMetrixClient$$Lambda$1() {
    }

    public static EndNotifier lambdaFactory$() {
        return instance;
    }

    public void complete(ProfilingResult profilingResult) {
        ThreatMetrixClient.lambda$initialize$0(profilingResult);
    }
}

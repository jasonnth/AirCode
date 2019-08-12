package org.spongycastle.math.p332ec.endo;

import org.spongycastle.math.p332ec.ECPointMap;

/* renamed from: org.spongycastle.math.ec.endo.ECEndomorphism */
public interface ECEndomorphism {
    ECPointMap getPointMap();

    boolean hasEfficientPointMap();
}

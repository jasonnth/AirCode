package com.airbnb.android.misnap;

import com.airbnb.android.core.BaseGraph;

public interface MiSnapGraph extends BaseGraph {
    void inject(MiSnapIdentityCaptureActivity miSnapIdentityCaptureActivity);

    void inject(MiSnapTakeSelfieActivity miSnapTakeSelfieActivity);
}

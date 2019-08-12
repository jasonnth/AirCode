package com.airbnb.android.lib.utils.erf;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.airbnb.erf.Experiment;

final /* synthetic */ class ErfOverrideActivity$$Lambda$2 implements OnItemClickListener {
    private final ErfOverrideActivity arg$1;

    private ErfOverrideActivity$$Lambda$2(ErfOverrideActivity erfOverrideActivity) {
        this.arg$1 = erfOverrideActivity;
    }

    public static OnItemClickListener lambdaFactory$(ErfOverrideActivity erfOverrideActivity) {
        return new ErfOverrideActivity$$Lambda$2(erfOverrideActivity);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.arg$1.editExperiment((Experiment) this.arg$1.experiments.get(i));
    }
}

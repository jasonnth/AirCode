package com.airbnb.android.lib.utils.erf;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.airbnb.android.lib.utils.erf.ErfOverrideActivity.EditExperimentDialog;

final /* synthetic */ class ErfOverrideActivity$EditExperimentDialog$$Lambda$1 implements OnItemClickListener {
    private final EditExperimentDialog arg$1;

    private ErfOverrideActivity$EditExperimentDialog$$Lambda$1(EditExperimentDialog editExperimentDialog) {
        this.arg$1 = editExperimentDialog;
    }

    public static OnItemClickListener lambdaFactory$(EditExperimentDialog editExperimentDialog) {
        return new ErfOverrideActivity$EditExperimentDialog$$Lambda$1(editExperimentDialog);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.arg$1.setTreatment((String) this.arg$1.mExperiment.getTreatments().get(i));
    }
}

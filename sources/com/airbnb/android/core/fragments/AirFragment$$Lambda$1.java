package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.fragments.AirFragment.DoneClickListener;

final /* synthetic */ class AirFragment$$Lambda$1 implements OnClickListener {
    private final DoneClickListener arg$1;

    private AirFragment$$Lambda$1(DoneClickListener doneClickListener) {
        this.arg$1 = doneClickListener;
    }

    public static OnClickListener lambdaFactory$(DoneClickListener doneClickListener) {
        return new AirFragment$$Lambda$1(doneClickListener);
    }

    public void onClick(View view) {
        this.arg$1.onDoneClick();
    }
}

package com.airbnb.android.core.viewcomponents.models;

import android.view.View;
import com.airbnb.epoxy.EpoxyModel;
import p032rx.functions.Action2;

final /* synthetic */ class EpoxyModelMixer$$Lambda$1 implements Action2 {
    private static final EpoxyModelMixer$$Lambda$1 instance = new EpoxyModelMixer$$Lambda$1();

    private EpoxyModelMixer$$Lambda$1() {
    }

    public static Action2 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj, Object obj2) {
        ((EpoxyModel) obj).bind((View) obj2);
    }
}

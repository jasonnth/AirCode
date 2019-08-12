package com.airbnb.android.core.viewcomponents.models;

import android.view.View;
import com.airbnb.epoxy.EpoxyModel;
import java.util.List;
import p032rx.functions.Action2;

final /* synthetic */ class EpoxyModelMixer$$Lambda$2 implements Action2 {
    private final List arg$1;

    private EpoxyModelMixer$$Lambda$2(List list) {
        this.arg$1 = list;
    }

    public static Action2 lambdaFactory$(List list) {
        return new EpoxyModelMixer$$Lambda$2(list);
    }

    public void call(Object obj, Object obj2) {
        ((EpoxyModel) obj).bind((View) obj2, this.arg$1);
    }
}

package com.airbnb.android.core.utils;

import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.ToggleActionRow.OnCheckedChangeListener;

final /* synthetic */ class RadioRowModelManager$$Lambda$1 implements OnCheckedChangeListener {
    private final RadioRowModelManager arg$1;
    private final Object arg$2;

    private RadioRowModelManager$$Lambda$1(RadioRowModelManager radioRowModelManager, Object obj) {
        this.arg$1 = radioRowModelManager;
        this.arg$2 = obj;
    }

    public static OnCheckedChangeListener lambdaFactory$(RadioRowModelManager radioRowModelManager, Object obj) {
        return new RadioRowModelManager$$Lambda$1(radioRowModelManager, obj);
    }

    public void onCheckedChanged(ToggleActionRow toggleActionRow, boolean z) {
        this.arg$1.setSelectedValue(this.arg$2, true);
    }
}

package com.airbnb.p027n2.interfaces;

import android.view.View;
import android.widget.Checkable;

/* renamed from: com.airbnb.n2.interfaces.SwitchRowInterface */
public interface SwitchRowInterface extends Checkable {

    /* renamed from: com.airbnb.n2.interfaces.SwitchRowInterface$OnCheckedChangeListener */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z);
    }

    View getView();

    void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener);
}

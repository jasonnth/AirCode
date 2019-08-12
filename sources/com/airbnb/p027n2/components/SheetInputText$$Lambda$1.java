package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.SheetInputText$$Lambda$1 */
final /* synthetic */ class SheetInputText$$Lambda$1 implements OnClickListener {
    private final SheetInputText arg$1;

    private SheetInputText$$Lambda$1(SheetInputText sheetInputText) {
        this.arg$1 = sheetInputText;
    }

    public static OnClickListener lambdaFactory$(SheetInputText sheetInputText) {
        return new SheetInputText$$Lambda$1(sheetInputText);
    }

    public void onClick(View view) {
        this.arg$1.onShowPasswordButtonClicked();
    }
}

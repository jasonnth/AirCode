package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.SheetStepperRow$$Lambda$1 */
final /* synthetic */ class SheetStepperRow$$Lambda$1 implements OnClickListener {
    private final SheetStepperRow arg$1;

    private SheetStepperRow$$Lambda$1(SheetStepperRow sheetStepperRow) {
        this.arg$1 = sheetStepperRow;
    }

    public static OnClickListener lambdaFactory$(SheetStepperRow sheetStepperRow) {
        return new SheetStepperRow$$Lambda$1(sheetStepperRow);
    }

    public void onClick(View view) {
        this.arg$1.setValue(this.arg$1.value + 1);
    }
}
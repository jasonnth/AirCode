package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.SheetStepperRow$$Lambda$2 */
final /* synthetic */ class SheetStepperRow$$Lambda$2 implements OnClickListener {
    private final SheetStepperRow arg$1;

    private SheetStepperRow$$Lambda$2(SheetStepperRow sheetStepperRow) {
        this.arg$1 = sheetStepperRow;
    }

    public static OnClickListener lambdaFactory$(SheetStepperRow sheetStepperRow) {
        return new SheetStepperRow$$Lambda$2(sheetStepperRow);
    }

    public void onClick(View view) {
        this.arg$1.setValue(this.arg$1.value - 1);
    }
}

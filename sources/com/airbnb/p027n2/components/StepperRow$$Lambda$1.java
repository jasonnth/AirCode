package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.StepperRow$$Lambda$1 */
final /* synthetic */ class StepperRow$$Lambda$1 implements OnClickListener {
    private final StepperRow arg$1;

    private StepperRow$$Lambda$1(StepperRow stepperRow) {
        this.arg$1 = stepperRow;
    }

    public static OnClickListener lambdaFactory$(StepperRow stepperRow) {
        return new StepperRow$$Lambda$1(stepperRow);
    }

    public void onClick(View view) {
        this.arg$1.handleValueChange(this.arg$1.value + 1);
    }
}

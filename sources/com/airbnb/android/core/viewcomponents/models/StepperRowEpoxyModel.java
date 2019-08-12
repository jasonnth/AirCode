package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.components.StepperRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.interfaces.StepperRowInterface.OnValueChangedListener;

public abstract class StepperRowEpoxyModel extends AirEpoxyModel<StepperRow> {
    CharSequence defaultText;
    CharSequence descriptionText;
    int maxValue = Integer.MAX_VALUE;
    int maxValueRes = 0;
    int minValue = 0;
    int minValueRes = 0;
    int pluralsRes = 0;
    int textRes;
    boolean useOldDesign;
    int value;
    OnValueChangedListener valueChangedListener;

    public void bind(StepperRow row) {
        super.bind(row);
        if (this.textRes != 0) {
            row.setText(this.textRes);
        } else {
            row.setText(this.defaultText);
        }
        if (this.pluralsRes != 0) {
            row.setValueResource(this.pluralsRes);
        }
        if (this.minValueRes != 0) {
            row.setMinValue(row.getResources().getInteger(this.minValueRes));
        } else {
            row.setMinValue(this.minValue);
        }
        if (this.maxValueRes != 0) {
            row.setMaxValue(row.getResources().getInteger(this.maxValueRes));
        } else {
            row.setMaxValue(this.maxValue);
        }
        row.setValue(this.value);
        row.setValueChangedListener(this.valueChangedListener);
        row.setDescription(this.descriptionText);
        row.useOldDesign(this.useOldDesign);
    }

    public void unbind(StepperRow row) {
        row.setValueChangedListener(null);
    }

    public int getDividerViewType() {
        return 0;
    }
}

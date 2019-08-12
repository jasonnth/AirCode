package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.SheetStepperRow_ViewBinding */
public final class SheetStepperRow_ViewBinding implements Unbinder {
    private SheetStepperRow target;

    public SheetStepperRow_ViewBinding(SheetStepperRow target2, View source) {
        this.target = target2;
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleView'", AirTextView.class);
        target2.valueView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.value, "field 'valueView'", AirTextView.class);
        target2.descriptionView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.description, "field 'descriptionView'", AirTextView.class);
        target2.minusButton = (ImageButton) Utils.findRequiredViewAsType(source, R.id.minus_button, "field 'minusButton'", ImageButton.class);
        target2.plusButton = (ImageButton) Utils.findRequiredViewAsType(source, R.id.plus_button, "field 'plusButton'", ImageButton.class);
    }

    public void unbind() {
        SheetStepperRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleView = null;
        target2.valueView = null;
        target2.descriptionView = null;
        target2.minusButton = null;
        target2.plusButton = null;
    }
}

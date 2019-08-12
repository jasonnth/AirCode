package com.airbnb.android.lib.tripassistant.amenities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class HTAmenityPickerActivity_ViewBinding implements Unbinder {
    private HTAmenityPickerActivity target;
    private View view2131755367;

    public HTAmenityPickerActivity_ViewBinding(HTAmenityPickerActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public HTAmenityPickerActivity_ViewBinding(final HTAmenityPickerActivity target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.selectionView = (HTAmenitiesSelectionView) Utils.findRequiredViewAsType(source, C0880R.C0882id.selection_view, "field 'selectionView'", HTAmenitiesSelectionView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.primary_button, "field 'saveButton' and method 'onPrimaryButtonClicked'");
        target2.saveButton = (AirButton) Utils.castView(view, C0880R.C0882id.primary_button, "field 'saveButton'", AirButton.class);
        this.view2131755367 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onPrimaryButtonClicked();
            }
        });
    }

    public void unbind() {
        HTAmenityPickerActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.selectionView = null;
        target2.saveButton = null;
        this.view2131755367.setOnClickListener(null);
        this.view2131755367 = null;
    }
}

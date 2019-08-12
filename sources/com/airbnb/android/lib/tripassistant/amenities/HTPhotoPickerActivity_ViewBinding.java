package com.airbnb.android.lib.tripassistant.amenities;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class HTPhotoPickerActivity_ViewBinding implements Unbinder {
    private HTPhotoPickerActivity target;
    private View view2131755367;

    public HTPhotoPickerActivity_ViewBinding(HTPhotoPickerActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public HTPhotoPickerActivity_ViewBinding(final HTPhotoPickerActivity target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.primary_button, "field 'saveButton' and method 'onSubmitClicked'");
        target2.saveButton = (AirButton) Utils.castView(view, C0880R.C0882id.primary_button, "field 'saveButton'", AirButton.class);
        this.view2131755367 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSubmitClicked();
            }
        });
    }

    public void unbind() {
        HTPhotoPickerActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.saveButton = null;
        this.view2131755367.setOnClickListener(null);
        this.view2131755367 = null;
    }
}
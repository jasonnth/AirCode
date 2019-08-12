package com.airbnb.android.lib.paidamenities.fragments.create;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

public class CreatedAmenityFragment_ViewBinding implements Unbinder {
    private CreatedAmenityFragment target;
    private View view2131756265;

    public CreatedAmenityFragment_ViewBinding(final CreatedAmenityFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.finish_button, "field 'finishButton' and method 'onClickNextButton'");
        target2.finishButton = (AirButton) Utils.castView(view, C0880R.C0882id.finish_button, "field 'finishButton'", AirButton.class);
        this.view2131756265 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNextButton();
            }
        });
        target2.serviceTerm = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.service_terms, "field 'serviceTerm'", AirTextView.class);
    }

    public void unbind() {
        CreatedAmenityFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.finishButton = null;
        target2.serviceTerm = null;
        this.view2131756265.setOnClickListener(null);
        this.view2131756265 = null;
    }
}

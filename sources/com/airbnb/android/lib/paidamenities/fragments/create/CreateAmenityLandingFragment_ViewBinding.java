package com.airbnb.android.lib.paidamenities.fragments.create;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CreateAmenityLandingFragment_ViewBinding implements Unbinder {
    private CreateAmenityLandingFragment target;
    private View view2131756035;

    public CreateAmenityLandingFragment_ViewBinding(final CreateAmenityLandingFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.next_button, "field 'nextButton' and method 'onClickNextButton'");
        target2.nextButton = (AirButton) Utils.castView(view, C0880R.C0882id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131756035 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNextButton();
            }
        });
    }

    public void unbind() {
        CreateAmenityLandingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.nextButton = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
    }
}
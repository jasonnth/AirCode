package com.airbnb.android.cohosting.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

public class RemoveCohostFragment_ViewBinding implements Unbinder {
    private RemoveCohostFragment target;
    private View view2131755487;

    public RemoveCohostFragment_ViewBinding(final RemoveCohostFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5658R.C5660id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.titleMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C5658R.C5660id.titleMarquee, "field 'titleMarquee'", DocumentMarquee.class);
        target2.explanationText = (AirTextView) Utils.findRequiredViewAsType(source, C5658R.C5660id.explanation, "field 'explanationText'", AirTextView.class);
        target2.responsiblityText = (SimpleTextRow) Utils.findRequiredViewAsType(source, C5658R.C5660id.listingOwnerResponsibility, "field 'responsiblityText'", SimpleTextRow.class);
        target2.currentHostingExplanation = (SimpleTextRow) Utils.findRequiredViewAsType(source, C5658R.C5660id.explanationForCurrentHosting, "field 'currentHostingExplanation'", SimpleTextRow.class);
        View view = Utils.findRequiredView(source, C5658R.C5660id.confirm_button, "field 'confirmButton' and method 'removeCohost'");
        target2.confirmButton = (AirButton) Utils.castView(view, C5658R.C5660id.confirm_button, "field 'confirmButton'", AirButton.class);
        this.view2131755487 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.removeCohost();
            }
        });
    }

    public void unbind() {
        RemoveCohostFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.titleMarquee = null;
        target2.explanationText = null;
        target2.responsiblityText = null;
        target2.currentHostingExplanation = null;
        target2.confirmButton = null;
        this.view2131755487.setOnClickListener(null);
        this.view2131755487 = null;
    }
}

package com.airbnb.android.cohosting.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BulletTextRow;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirTextView;

public class CohostingServicesIntroFragment_ViewBinding implements Unbinder {
    private CohostingServicesIntroFragment target;
    private View view2131755496;

    public CohostingServicesIntroFragment_ViewBinding(final CohostingServicesIntroFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5658R.C5660id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.titleMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C5658R.C5660id.explanation_title, "field 'titleMarquee'", DocumentMarquee.class);
        target2.explanationSubtitle = (AirTextView) Utils.findRequiredViewAsType(source, C5658R.C5660id.explanation_subtitle, "field 'explanationSubtitle'", AirTextView.class);
        target2.interactionTextForListingAdmin = (BulletTextRow) Utils.findRequiredViewAsType(source, C5658R.C5660id.interaction_text_for_listing_admin, "field 'interactionTextForListingAdmin'", BulletTextRow.class);
        target2.interactionTextForCohost = (BulletTextRow) Utils.findRequiredViewAsType(source, C5658R.C5660id.interaction_text_for_cohost, "field 'interactionTextForCohost'", BulletTextRow.class);
        target2.divider = Utils.findRequiredView(source, C5658R.C5660id.section_divider, "field 'divider'");
        target2.constrainsText = (AirTextView) Utils.findRequiredViewAsType(source, C5658R.C5660id.cohosts_constrains, "field 'constrainsText'", AirTextView.class);
        target2.guestsText = (SimpleTextRow) Utils.findRequiredViewAsType(source, C5658R.C5660id.cohosts_what_guests_see, "field 'guestsText'", SimpleTextRow.class);
        target2.termsText = (AirTextView) Utils.findRequiredViewAsType(source, C5658R.C5660id.terms, "field 'termsText'", AirTextView.class);
        View view = Utils.findRequiredView(source, C5658R.C5660id.learn_more, "method 'showMoreInformation'");
        this.view2131755496 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showMoreInformation();
            }
        });
    }

    public void unbind() {
        CohostingServicesIntroFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.titleMarquee = null;
        target2.explanationSubtitle = null;
        target2.interactionTextForListingAdmin = null;
        target2.interactionTextForCohost = null;
        target2.divider = null;
        target2.constrainsText = null;
        target2.guestsText = null;
        target2.termsText = null;
        this.view2131755496.setOnClickListener(null);
        this.view2131755496 = null;
    }
}

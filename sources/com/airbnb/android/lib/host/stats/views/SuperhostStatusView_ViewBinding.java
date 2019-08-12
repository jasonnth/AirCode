package com.airbnb.android.lib.host.stats.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SectionHeader;

public class SuperhostStatusView_ViewBinding implements Unbinder {
    private SuperhostStatusView target;
    private View view2131756183;

    public SuperhostStatusView_ViewBinding(SuperhostStatusView target2) {
        this(target2, target2);
    }

    public SuperhostStatusView_ViewBinding(final SuperhostStatusView target2, View source) {
        this.target = target2;
        target2.title = (SectionHeader) Utils.findRequiredViewAsType(source, C0880R.C0882id.superhost_title, "field 'title'", SectionHeader.class);
        target2.dateTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.date_title, "field 'dateTitle'", TextView.class);
        target2.dateText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.next_assessment_date, "field 'dateText'", TextView.class);
        target2.aboutAssessmentText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.about_assessment, "field 'aboutAssessmentText'", TextView.class);
        target2.superhostBadgeImageView = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.superhost_badge, "field 'superhostBadgeImageView'", ImageView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.learn_more, "method 'onLearnMoreClick'");
        this.view2131756183 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onLearnMoreClick(p0);
            }
        });
    }

    public void unbind() {
        SuperhostStatusView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.dateTitle = null;
        target2.dateText = null;
        target2.aboutAssessmentText = null;
        target2.superhostBadgeImageView = null;
        this.view2131756183.setOnClickListener(null);
        this.view2131756183 = null;
    }
}

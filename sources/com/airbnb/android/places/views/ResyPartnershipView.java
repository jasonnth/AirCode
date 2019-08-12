package com.airbnb.android.places.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class ResyPartnershipView extends LinearLayout implements DividerView {
    @BindView
    View sectionDivider;

    public ResyPartnershipView(Context context) {
        super(context);
        init();
    }

    public ResyPartnershipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ResyPartnershipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C7627R.layout.view_resy_partnership, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void showDivider(boolean show) {
        ViewLibUtils.setVisibleIf(this.sectionDivider, show);
    }
}

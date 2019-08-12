package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CohostLeadsCenterCancelQuoteFragment extends CohostLeadsCenterBaseFragment {
    @BindView
    AirButton button;
    @BindView
    AirRecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static CohostLeadsCenterCancelQuoteFragment create(String firstName) {
        return (CohostLeadsCenterCancelQuoteFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CohostLeadsCenterCancelQuoteFragment()).putString(CohostingConstants.FIRST_NAME_FIELD, firstName)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.f7678x694f1e94, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        setControllerAndBuildModels();
        this.button.setText(C5658R.string.cohosting_leads_center_cancel_quote_button_text);
        return view;
    }

    private void setControllerAndBuildModels() {
        this.recyclerView.setStaticModels(new DocumentMarqueeEpoxyModel_().titleRes(C5658R.string.cohosting_leads_center_cancel_quote_title).m4536id((CharSequence) "marquee"), new SimpleTextRowEpoxyModel_().text(getContext().getString(C5658R.string.cohosting_leads_center_cancel_quote_explanation, new Object[]{getArguments().getString(CohostingConstants.FIRST_NAME_FIELD)})).m5580id((CharSequence) "text"));
    }
}

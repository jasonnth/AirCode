package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.epoxycontrollers.CohostLeadsCenterSendQuoteEpoxyController;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;

public class CohostLeadsCenterSendQuoteFragment extends CohostLeadsCenterBaseFragment {
    @BindView
    FixedActionFooter button;
    @BindView
    AirRecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static CohostLeadsCenterSendQuoteFragment create(String firstName) {
        return (CohostLeadsCenterSendQuoteFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CohostLeadsCenterSendQuoteFragment()).putString(CohostingConstants.FIRST_NAME_FIELD, firstName)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.f7679x103b0287, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        String firstName = getArguments().getString(CohostingConstants.FIRST_NAME_FIELD);
        this.recyclerView.setEpoxyController(new CohostLeadsCenterSendQuoteEpoxyController(getContext(), firstName));
        this.button.setButtonText((CharSequence) getContext().getString(C5658R.string.cohosting_leads_center_quote_send_to_action, new Object[]{firstName}));
        return view;
    }
}

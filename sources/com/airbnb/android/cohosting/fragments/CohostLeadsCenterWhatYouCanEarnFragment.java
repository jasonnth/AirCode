package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.epoxycontrollers.CohostLeadsCenterWhatYouCanEarnEpoxyController;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;

public class CohostLeadsCenterWhatYouCanEarnFragment extends CohostLeadsCenterBaseFragment {
    @BindView
    AirRecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static CohostLeadsCenterWhatYouCanEarnFragment create(long inquiryId) {
        return (CohostLeadsCenterWhatYouCanEarnFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CohostLeadsCenterWhatYouCanEarnFragment()).putLong(CohostingConstants.INQUIRY_ID_FIELD, inquiryId)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.fragment_recycler_view_with_toolbar_dark_foreground, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.recyclerView.setEpoxyController(new CohostLeadsCenterWhatYouCanEarnEpoxyController(getContext(), this.controller, getArguments().getLong(CohostingConstants.INQUIRY_ID_FIELD)));
        return view;
    }
}

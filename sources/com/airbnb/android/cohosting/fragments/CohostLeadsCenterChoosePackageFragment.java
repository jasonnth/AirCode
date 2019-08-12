package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.epoxycontrollers.CohostLeadsCenterChoosePackageEpoxyController;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;

public class CohostLeadsCenterChoosePackageFragment extends CohostLeadsCenterBaseFragment {
    @BindView
    FixedActionFooter button;
    @BindView
    AirRecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static CohostLeadsCenterChoosePackageFragment create(String firstName) {
        return (CohostLeadsCenterChoosePackageFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CohostLeadsCenterChoosePackageFragment()).putString(CohostingConstants.FIRST_NAME_FIELD, firstName)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.f7679x103b0287, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.recyclerView.setEpoxyController(new CohostLeadsCenterChoosePackageEpoxyController(getContext(), getArguments().getString(CohostingConstants.FIRST_NAME_FIELD)));
        this.button.setButtonText((CharSequence) getContext().getString(C5658R.string.save));
        return view;
    }
}

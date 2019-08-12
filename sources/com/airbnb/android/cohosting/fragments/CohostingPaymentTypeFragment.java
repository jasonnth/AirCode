package com.airbnb.android.cohosting.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingPaymentTypeEpoxyController;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;

public class CohostingPaymentTypeFragment extends AirFragment {
    private CohostingPaymentTypeEpoxyController epoxyController;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static CohostingPaymentTypeFragment create(FeeType feeType, String managerFirstName) {
        return (CohostingPaymentTypeFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CohostingPaymentTypeFragment()).putSerializable("fee_type", feeType)).putString(CohostingConstants.FIRST_NAME_FIELD, managerFirstName)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.epoxyController = new CohostingPaymentTypeEpoxyController(getContext(), (FeeType) getArguments().getSerializable("fee_type"), getArguments().getString(CohostingConstants.FIRST_NAME_FIELD));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.fragment_cohosting_payment_type, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        return view;
    }

    @OnClick
    public void saveFeeType() {
        getActivity().setResult(-1, new Intent().putExtra("fee_type", this.epoxyController.getFeeType()));
        getActivity().finish();
    }
}

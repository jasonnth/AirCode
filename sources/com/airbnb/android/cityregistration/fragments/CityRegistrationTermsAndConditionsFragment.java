package com.airbnb.android.cityregistration.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.cityregistration.adapters.CityRegistrationTermsAndConditionsAdapter;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.primitives.AirButton;

public class CityRegistrationTermsAndConditionsFragment extends CityRegistrationBaseSubmissionFragment {
    private CityRegistrationTermsAndConditionsAdapter adapter;
    @BindView
    AirButton lysSaveButton;
    @BindView
    ToggleActionRow toggleActionRow;

    public static CityRegistrationTermsAndConditionsFragment create() {
        return new CityRegistrationTermsAndConditionsFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new CityRegistrationTermsAndConditionsAdapter(this.listingRegistrationProcess.getContent().getTermsAndConditions(), getContext(), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        View view = inflater.inflate(C5630R.layout.fragment_city_registration_terms_and_conditions, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        this.saveButton.setText(C5630R.string.submit);
        this.lysSaveButton.setText(C5630R.string.submit);
        this.toggleActionRow.setTitle((CharSequence) this.listingRegistrationProcess.getContent().getTermsAndConditions().getFooter().getTexts().get(0));
        this.toggleActionRow.setOnClickListener(CityRegistrationTermsAndConditionsFragment$$Lambda$1.lambdaFactory$(this));
        AirButton airButton = this.saveButton;
        if (!this.controller.isLYS()) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleIf((View) airButton, z);
        ViewUtils.setVisibleIf((View) this.lysSaveButton, this.controller.isLYS());
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(CityRegistrationTermsAndConditionsFragment cityRegistrationTermsAndConditionsFragment, View v) {
        cityRegistrationTermsAndConditionsFragment.saveButton.setEnabled(cityRegistrationTermsAndConditionsFragment.toggleActionRow.isChecked());
        cityRegistrationTermsAndConditionsFragment.lysSaveButton.setEnabled(cityRegistrationTermsAndConditionsFragment.toggleActionRow.isChecked());
    }

    @OnClick
    public void onSubmit() {
        super.onSubmit();
    }

    @OnClick
    public void lysOnSubmit() {
        super.onSubmit();
    }
}

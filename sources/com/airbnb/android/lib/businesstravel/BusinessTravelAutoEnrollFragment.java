package com.airbnb.android.lib.businesstravel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.businesstravel.BusinessTravelAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.EditProfileActivity;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class BusinessTravelAutoEnrollFragment extends AirFragment {
    private static final String ARG_EMAIL_DOMAIN = "arg_email_domain";
    @State
    String emailDomain;
    @BindView
    AirButton gotItButton;
    @BindView
    SimpleTextRow legalDisclaimerRow;
    @BindView
    SheetMarquee sheetMarquee;

    public static BusinessTravelAutoEnrollFragment forEmailDomain(String emailDomain2) {
        return (BusinessTravelAutoEnrollFragment) ((FragmentBundleBuilder) FragmentBundler.make(new BusinessTravelAutoEnrollFragment()).putString(ARG_EMAIL_DOMAIN, emailDomain2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_business_travel_auto_enroll, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.emailDomain = getArguments().getString(ARG_EMAIL_DOMAIN);
        }
        this.gotItButton.setText(C0880R.string.bt_auto_enroll_confirm_button);
        this.gotItButton.setOnClickListener(BusinessTravelAutoEnrollFragment$$Lambda$1.lambdaFactory$(this));
        this.sheetMarquee.setSubtitle(getString(C0880R.string.bt_auto_enroll_subtitle, this.emailDomain));
        String profileSettings = getString(C0880R.string.bt_auto_enroll_opt_out_profile_settings);
        this.legalDisclaimerRow.setupLinkedText(getString(C0880R.string.bt_auto_enroll_opt_out, profileSettings), profileSettings, C0880R.color.n2_white, BusinessTravelAutoEnrollFragment$$Lambda$2.lambdaFactory$(this));
        BusinessTravelAnalytics.trackAutoEnrollEvent("impression");
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(BusinessTravelAutoEnrollFragment businessTravelAutoEnrollFragment, View v) {
        BusinessTravelAnalytics.trackAutoEnrollEvent("closed_modal");
        businessTravelAutoEnrollFragment.getActivity().finish();
    }

    /* access modifiers changed from: private */
    public void goToEditProfile() {
        BusinessTravelAnalytics.trackAutoEnrollEvent("clicked_profile_link");
        startActivity(EditProfileActivity.intentForDefault(getActivity()));
        getActivity().finish();
    }
}

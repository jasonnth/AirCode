package com.airbnb.android.p011p3;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;

/* renamed from: com.airbnb.android.p3.ReferralCreditDialogFragment */
public class ReferralCreditDialogFragment extends AirDialogFragment {
    private static final String ARG_REFERRAL_STATUS = "arg_referral_status";
    public static final double WIDTH_PERCENT = 0.7d;
    @BindView
    DocumentMarquee couponMarquee;
    @BindView
    AirToolbar toolbar;

    public static ReferralCreditDialogFragment newInstance(ReferralStatusForMobile referralStatus) {
        return (ReferralCreditDialogFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ReferralCreditDialogFragment()).putParcelable(ARG_REFERRAL_STATUS, referralStatus)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7532R.layout.fragment_referral_credit, container, false);
        bindViews(view);
        this.toolbar.setNavigationOnClickListener(ReferralCreditDialogFragment$$Lambda$1.lambdaFactory$(this));
        populateViews();
        return view;
    }

    private void populateViews() {
        ReferralStatusForMobile referralStatus = (ReferralStatusForMobile) getArguments().getParcelable(ARG_REFERRAL_STATUS);
        String couponAmount = referralStatus.getAvailableCreditLocalized();
        String minTripCost = referralStatus.getAvailableCreditMinTripValueLocalizedRequirement();
        this.couponMarquee.setCaption((CharSequence) SpannableUtils.appendBoldedSubString(String.format(getResources().getString(C7532R.string.referral_credit_fragment_body), new Object[]{couponAmount, minTripCost}) + "\n", String.format(getResources().getString(C7532R.string.referral_credit_expires), new Object[]{referralStatus.getAvailableCreditExpiration().formatDate(DateFormat.getMediumDateFormat(getContext()))}), getContext()));
        this.couponMarquee.setLinkText(C7532R.string.referral_credit_see_terms);
        this.couponMarquee.setLinkClickListener(ReferralCreditDialogFragment$$Lambda$4.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void launchTravelCreditTermsofService() {
        startActivity(WebViewIntentBuilder.newBuilder(getActivity(), getString(C7532R.string.tos_url_referrals)).title(C7532R.string.terms_and_conditions).toIntent());
    }

    public boolean isLeafDialog() {
        return true;
    }

    /* access modifiers changed from: protected */
    public int leafDialogWidth() {
        return (int) (((double) ViewUtils.getScreenWidth(getContext())) * 0.7d);
    }

    /* access modifiers changed from: protected */
    public int leafDialogHeight() {
        return -2;
    }
}

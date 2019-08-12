package com.airbnb.android.identity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.enums.VerificationFlowText;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class AccountVerificationFinishFragment extends BaseAccountVerificationFragment {
    private static final String ARG_HOST = "arg_host";
    public static final String ARG_VERIFICATION_FLOW = "arg_verification_flow";
    @BindView
    AirButton button;
    @State
    VerificationFlow flow;
    @State
    User host;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    SheetMarquee marquee;

    public static AccountVerificationFinishFragment newInstance(User host2, VerificationFlow verificationFlow) {
        return (AccountVerificationFinishFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AccountVerificationFinishFragment()).putParcelable("arg_host", host2)).putSerializable("arg_verification_flow", verificationFlow)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String firstName;
        View view = inflater.inflate(C6533R.layout.fragment_account_verification_finish, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.host = (User) getArguments().getParcelable("arg_host");
            this.flow = getVerificationFlow();
        }
        VerificationFlowText verificationFlowText = this.flow.getText();
        this.marquee.setTitle(verificationFlowText.getFinishTitle(getContext(), this.mAccountManager.getCurrentUser().getFirstName()));
        SheetMarquee sheetMarquee = this.marquee;
        Context context = getContext();
        String firstName2 = this.mAccountManager.getCurrentUser().getFirstName();
        if (this.host == null) {
            firstName = null;
        } else {
            firstName = this.host.getFirstName();
        }
        sheetMarquee.setSubtitle(verificationFlowText.getFinishSubtitle(context, firstName2, firstName));
        this.button.setText(verificationFlowText.getFinishButtonLabel());
        ViewUtils.setVisibleIf((View) this.jellyfishView, IdentityStyle.getStyle(getVerificationFlow()).hasJellyFish);
        this.controller.getIdentityJitneyLogger().logImpression(getVerificationFlow(), this.controller.getUser(), null, Page.flow_completion);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onFinishBookingClick() {
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), "continue_booking_button");
        this.controller.getIdentityJitneyLogger().logClick(getVerificationFlow(), this.controller.getUser(), null, Page.flow_completion, Element.navigation_button_finish);
        Intent data = new Intent();
        data.putExtra("extra_verification_flow", this.flow.ordinal());
        getActivity().setResult(-1, data);
        getActivity().finish();
    }

    public void onResume() {
        super.onResume();
    }

    public Strap getNavigationTrackingParams() {
        return getVerificationFlow().getNavigationTrackingParams(getContext());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationComplete;
    }
}

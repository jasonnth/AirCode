package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.p011p3.C7532R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.DocumentMarquee;
import icepick.State;

public class SouthKoreanCancellationPolicyFragment extends AirFragment {
    private static final String ARG_PROVIDER = "trip_provider";
    private static final String MQ_CAPTION_SOURCE = "marquee_caption";
    private static final String MQ_TITLE_SOURCE = "marquee_title";
    private Listener listener;
    @BindView
    DocumentMarquee marquee;
    @State
    TripInformationProvider provider;

    public interface Listener {
        void onAcceptCancellationAgreement();
    }

    public static SouthKoreanCancellationPolicyFragment newInstance(int marqueeTitle, int marqueeCaption, TripInformationProvider provider2) {
        return (SouthKoreanCancellationPolicyFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SouthKoreanCancellationPolicyFragment()).putParcelable("trip_provider", provider2)).putInt(MQ_TITLE_SOURCE, marqueeTitle)).putInt(MQ_CAPTION_SOURCE, marqueeCaption)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_south_korean_cancellation_policy, container, false);
        bindViews(view);
        this.marquee.setTitle(getArguments().getInt(MQ_TITLE_SOURCE));
        this.marquee.setCaption(getArguments().getInt(MQ_CAPTION_SOURCE));
        this.marquee.setLinkText(C0880R.string.go_to_full_policy_link);
        this.marquee.setLinkClickListener(SouthKoreanCancellationPolicyFragment$$Lambda$1.lambdaFactory$(this));
        this.provider = (TripInformationProvider) getArguments().getParcelable("trip_provider");
        return view;
    }

    @OnClick
    public void clickContinue() {
        getFragmentManager().popBackStack();
        if (this.listener != null) {
            this.listener.onAcceptCancellationAgreement();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener interface");
        }
    }

    /* access modifiers changed from: private */
    public void goToSouthKoreanCancellationPolicy() {
        startActivity(WebViewIntentBuilder.newBuilder(getActivity(), getString(C0880R.string.south_korean_cancellation_policy_url)).title(C7532R.string.terms_and_conditions).toIntent());
    }
}

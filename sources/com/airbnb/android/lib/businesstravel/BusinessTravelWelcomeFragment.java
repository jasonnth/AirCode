package com.airbnb.android.lib.businesstravel;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.utils.SpannableUtils.UrlText;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData;
import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.LegalDisclaimer;
import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Link;
import com.airbnb.android.lib.businesstravel.network.BusinessTravelWelcomeContentRequest;
import com.airbnb.android.lib.businesstravel.network.BusinessTravelWelcomeContentResponse;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.EditorialMarquee;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.List;
import p032rx.Observer;

public class BusinessTravelWelcomeFragment extends AirFragment {
    private static final String ARG_WORK_EMAIL = "arg_work_email";
    @BindView
    LinearLayout bottomBar;
    BusinessTravelJitneyLogger businessTravelJitneyLogger;
    @BindView
    EditorialMarquee editorialMarquee;
    @BindView
    AirButton gotItButton;
    @BindView
    LoadingView loadingView;
    @BindView
    SimpleTextRow textRow;
    @BindView
    AirToolbar toolbar;
    @State
    BusinessTravelWelcomeData welcomeContent;
    final RequestListener<BusinessTravelWelcomeContentResponse> welcomeDataRequestListener = new C0699RL().onResponse(BusinessTravelWelcomeFragment$$Lambda$1.lambdaFactory$(this)).onError(BusinessTravelWelcomeFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    String workEmail;

    public static BusinessTravelWelcomeFragment instanceForWorkEmail(String workEmail2) {
        return (BusinessTravelWelcomeFragment) ((FragmentBundleBuilder) FragmentBundler.make(new BusinessTravelWelcomeFragment()).putString(ARG_WORK_EMAIL, workEmail2)).build();
    }

    static /* synthetic */ void lambda$new$0(BusinessTravelWelcomeFragment businessTravelWelcomeFragment, BusinessTravelWelcomeContentResponse response) {
        businessTravelWelcomeFragment.welcomeContent = response.businessTravelWelcomeData;
        businessTravelWelcomeFragment.updateContentUI();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (savedInstanceState == null) {
            this.workEmail = getArguments().getString(ARG_WORK_EMAIL);
            BusinessTravelWelcomeContentRequest.forUserId(this.mAccountManager.getCurrentUserId()).withListener((Observer) this.welcomeDataRequestListener).execute(this.requestManager);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_business_travel_welcome, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.loadingView.setVisibility(0);
        this.businessTravelJitneyLogger.logBTEmailVerifiedWelcomeModalImpression(this.workEmail);
        return view;
    }

    private void updateContentUI() {
        this.loadingView.setVisibility(8);
        this.editorialMarquee.setVisibility(0);
        this.editorialMarquee.setImageUrl(this.welcomeContent.marqueeImageUrl());
        this.editorialMarquee.setDescription(this.welcomeContent.marqueeBody());
        this.editorialMarquee.setTitle((CharSequence) this.welcomeContent.marqueeTitle());
        this.gotItButton.setText(this.welcomeContent.buttonText());
        this.gotItButton.setOnClickListener(BusinessTravelWelcomeFragment$$Lambda$3.lambdaFactory$(this));
        this.gotItButton.setVisibility(0);
        this.bottomBar.setVisibility(0);
        this.textRow.setText((CharSequence) getClickableText());
        this.textRow.setMovementMethod(LinkMovementMethod.getInstance());
    }

    static /* synthetic */ void lambda$updateContentUI$2(BusinessTravelWelcomeFragment businessTravelWelcomeFragment, View v) {
        businessTravelWelcomeFragment.getActivity().finish();
        businessTravelWelcomeFragment.businessTravelJitneyLogger.logBTEmailVerifiedWelcomeModalClickGotIt(businessTravelWelcomeFragment.workEmail);
    }

    private SpannableString getClickableText() {
        return SpannableUtils.createColoredClickableUrls(getActivity(), this.welcomeContent.legalDisclaimer().fullText(), convertLinksToUrlTexts(this.welcomeContent.legalDisclaimer()), C0880R.color.c_babu);
    }

    private List<UrlText> convertLinksToUrlTexts(LegalDisclaimer legalDisclaimer) {
        return FluentIterable.from((Iterable<E>) legalDisclaimer.links()).transform(BusinessTravelWelcomeFragment$$Lambda$4.lambdaFactory$()).toList();
    }

    static /* synthetic */ UrlText lambda$convertLinksToUrlTexts$3(Link link) {
        return new UrlText(link.text(), link.url());
    }
}

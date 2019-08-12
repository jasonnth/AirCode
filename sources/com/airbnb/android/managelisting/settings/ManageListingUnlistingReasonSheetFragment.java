package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.models.SupportPhoneNumber;
import com.airbnb.android.core.requests.SupportPhoneNumbersRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.responses.SupportPhoneNumbersResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.PhoneUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.analytics.UnlistAnalytics;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.HeroMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import icepick.State;
import p032rx.Observer;

public class ManageListingUnlistingReasonSheetFragment extends ManageListingBaseFragment {
    private static final String PARAM_REASON = "param_unlist_reason";
    private static final int SMART_PRICING_NAV_DELAY_MILLIS = 500;
    private AirButton activeButton;
    @BindView
    AirTextView captionText;
    @State
    SupportPhoneNumber cxNumber;
    @BindView
    AirButton firstButton;
    @BindView
    HeroMarquee heroMarquee;
    @State
    int reason;
    @BindView
    AirButton secondButton;
    NonResubscribableRequestListener<SupportPhoneNumbersResponse> supportNumberListener = new C0699RL().onResponse(ManageListingUnlistingReasonSheetFragment$$Lambda$1.lambdaFactory$(this)).buildWithoutResubscription();
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingUnlistingReasonSheetFragment$$Lambda$2.lambdaFactory$(this)).onError(ManageListingUnlistingReasonSheetFragment$$Lambda$3.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(ManageListingUnlistingReasonSheetFragment manageListingUnlistingReasonSheetFragment, SupportPhoneNumbersResponse response) {
        manageListingUnlistingReasonSheetFragment.cxNumber = response.numbers.size() > 0 ? (SupportPhoneNumber) response.numbers.get(0) : null;
    }

    static /* synthetic */ void lambda$new$1(ManageListingUnlistingReasonSheetFragment manageListingUnlistingReasonSheetFragment, SimpleListingResponse response) {
        manageListingUnlistingReasonSheetFragment.controller.setListing(response.listing);
        manageListingUnlistingReasonSheetFragment.controller.actionExecutor.popToFragment(ManageListingStatusSettingFragment.class);
    }

    static /* synthetic */ void lambda$new$2(ManageListingUnlistingReasonSheetFragment manageListingUnlistingReasonSheetFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(manageListingUnlistingReasonSheetFragment.getView(), e);
        manageListingUnlistingReasonSheetFragment.activeButton.setState(AirButton.State.Normal);
        manageListingUnlistingReasonSheetFragment.setButtonsEnabled(true);
    }

    public static Fragment forReason(int reason2) {
        return ((FragmentBundleBuilder) FragmentBundler.make(new ManageListingUnlistingReasonSheetFragment()).putInt(PARAM_REASON, reason2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SupportPhoneNumbersRequest().withListener((Observer) this.supportNumberListener).execute(this.requestManager);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_manage_listing_unlist_reason_sheet, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.reason = getArguments().getInt(PARAM_REASON);
        switch (this.reason) {
            case 0:
                noAccess();
                break;
            case 1:
                tooMuchWork();
                break;
            case 2:
                notEarningEnough();
                break;
            case 3:
                legalQuestion();
                break;
            case 4:
                trustQuestion();
                break;
            case 5:
                negativeExperience();
                break;
            default:
                throw new RuntimeException("That reason isn't implemented yet");
        }
        return view;
    }

    private void noAccess() {
        this.heroMarquee.setTitle(C7368R.string.manage_listing_unlist_reason_no_access_title);
        this.heroMarquee.setCaption(C7368R.string.manage_listing_unlist_reason_no_access_caption);
        this.heroMarquee.setFirstButtonText(C7368R.string.unlist);
        this.heroMarquee.setFirstButtonClickListener(ManageListingUnlistingReasonSheetFragment$$Lambda$4.lambdaFactory$(this));
    }

    private void tooMuchWork() {
        this.heroMarquee.setTitle(C7368R.string.manage_listing_unlist_reason_too_much_work_title);
        this.heroMarquee.setCaption(C7368R.string.manage_listing_unlist_reason_too_much_work_caption);
        this.heroMarquee.setFirstButtonText(C7368R.string.snooze_listing_btn);
        this.heroMarquee.setFirstButtonClickListener(ManageListingUnlistingReasonSheetFragment$$Lambda$5.lambdaFactory$(this));
        this.heroMarquee.setSecondButtonText(C7368R.string.unlist);
        this.heroMarquee.setSecondButtonClickListener(ManageListingUnlistingReasonSheetFragment$$Lambda$6.lambdaFactory$(this));
    }

    private void notEarningEnough() {
        if (this.controller.getListing().isSmartPricingAvailable()) {
            this.heroMarquee.setTitle(C7368R.string.manage_listing_unlist_reason_not_earning_enough_title_with_sp);
            this.heroMarquee.setCaption(C7368R.string.manage_listing_unlist_reason_not_earning_enough_caption_with_sp);
            this.heroMarquee.setFirstButtonText(C7368R.string.manage_listing_smart_pricing_try_it_button);
            this.heroMarquee.setFirstButtonClickListener(ManageListingUnlistingReasonSheetFragment$$Lambda$7.lambdaFactory$(this));
            this.heroMarquee.setSecondButtonText(C7368R.string.unlist);
            this.heroMarquee.setSecondButtonClickListener(ManageListingUnlistingReasonSheetFragment$$Lambda$8.lambdaFactory$(this));
            return;
        }
        this.heroMarquee.setTitle(C7368R.string.manage_listing_unlist_reason_not_earning_enough_title_with_no_sp);
        this.heroMarquee.setCaption(C7368R.string.f10104xcda93361);
        this.heroMarquee.setFirstButtonText(C7368R.string.unlist);
        this.heroMarquee.setFirstButtonClickListener(ManageListingUnlistingReasonSheetFragment$$Lambda$9.lambdaFactory$(this));
    }

    private void legalQuestion() {
        this.heroMarquee.setTitle(C7368R.string.manage_listing_unlist_reason_law_question_title);
        setLegalQuestionCaptionText();
        this.heroMarquee.setSecondButtonText(C7368R.string.unlist);
        this.heroMarquee.setSecondButtonClickListener(ManageListingUnlistingReasonSheetFragment$$Lambda$10.lambdaFactory$(this));
    }

    private void trustQuestion() {
        this.heroMarquee.setTitle(C7368R.string.manage_listing_unlist_reason_trust_question_title);
        this.heroMarquee.setCaption(C7368R.string.manage_listing_unlist_reason_trust_question_caption);
        this.heroMarquee.setFirstButtonText(C7368R.string.manage_listing_unlist_reason_trust_question_host_guarantee_btn);
        this.heroMarquee.setFirstButtonClickListener(ManageListingUnlistingReasonSheetFragment$$Lambda$11.lambdaFactory$(this));
        this.heroMarquee.setSecondButtonText(C7368R.string.unlist);
        this.heroMarquee.setSecondButtonClickListener(ManageListingUnlistingReasonSheetFragment$$Lambda$12.lambdaFactory$(this));
    }

    private void negativeExperience() {
        this.heroMarquee.setTitle(C7368R.string.manage_listing_unlist_reason_negative_experience_title);
        this.heroMarquee.setCaption(C7368R.string.manage_listing_unlist_reason_negative_experience_caption);
        this.heroMarquee.setFirstButtonText(C7368R.string.contact_airbnb);
        this.heroMarquee.setFirstButtonClickListener(ManageListingUnlistingReasonSheetFragment$$Lambda$13.lambdaFactory$(this));
        this.heroMarquee.setSecondButtonText(C7368R.string.unlist);
        this.heroMarquee.setSecondButtonClickListener(ManageListingUnlistingReasonSheetFragment$$Lambda$14.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void unlist(View v) {
        this.activeButton = (AirButton) v;
        this.activeButton.setState(AirButton.State.Loading);
        setButtonsEnabled(false);
        UnlistAnalytics.trackSubmitUnlistWithUnlistReason(this.controller.getListing(), this.reason);
        UpdateListingRequest.forField(this.controller.getListing().getId(), ListingRequestConstants.JSON_HAS_AVAILABILITY, Boolean.valueOf(false)).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void contactUs() {
        PhoneUtil.showPhoneNumberActionSheet(getContext(), this.cxNumber == null ? getContext().getString(C7368R.string.support_phone_number) : this.cxNumber.getNumber());
    }

    /* access modifiers changed from: private */
    public void navToSmartPricing() {
        ManageListingActionExecutor actionExecutor = this.controller.actionExecutor;
        Handler handler = getView().getHandler();
        this.controller.actionExecutor.popToHome();
        handler.postDelayed(ManageListingUnlistingReasonSheetFragment$$Lambda$15.lambdaFactory$(actionExecutor), 500);
    }

    private void setButtonsEnabled(boolean enabled) {
        this.firstButton.setEnabled(enabled);
        this.secondButton.setEnabled(enabled);
    }

    private void setLegalQuestionCaptionText() {
        this.heroMarquee.setCaption((CharSequence) getClickableHelpCenterText());
        this.captionText.setMovementMethod(LinkMovementMethod.getInstance());
        this.captionText.setLinkTextColor(ContextCompat.getColor(getContext(), C7368R.color.n2_white));
    }

    private SpannableStringBuilder getClickableHelpCenterText() {
        String talkingToOthersLink = getString(C7368R.string.f10103x3ded6e66);
        String legalIssuesLink = getString(C7368R.string.f10102x54332bf8);
        String taxesLink = getString(C7368R.string.manage_listing_unlist_reason_law_question_caption_link_taxes);
        SpannableStringBuilder builder = new SpannableStringBuilder(getString(C7368R.string.manage_listing_unlist_reason_law_question_caption)).append("\n\n");
        appendToBuilder(builder, talkingToOthersLink, getClickableSpanForArticle(HelpCenterArticle.TALK_TO_OTHERS), 33).append("\n\n");
        appendToBuilder(builder, legalIssuesLink, getClickableSpanForArticle(HelpCenterArticle.LEGAL_ISSUES), 33).append("\n\n");
        appendToBuilder(builder, taxesLink, getClickableSpanForArticle(HelpCenterArticle.HOST_TAXES), 33);
        return builder;
    }

    private ClickableSpan getClickableSpanForArticle(final int articleNum) {
        return new ClickableSpan() {
            public void onClick(View widget) {
                ManageListingUnlistingReasonSheetFragment.this.logAndNavToHelpCenterArticle(ManageListingUnlistingReasonSheetFragment.this.getContext(), articleNum);
            }
        };
    }

    /* access modifiers changed from: private */
    public void logAndNavToHelpCenterArticle(Context context, int articleNum) {
        UnlistAnalytics.trackClickLinkInUnlistDialog(this.controller.getListing(), HelpCenterIntents.getHelpCenterArticleUrl(context, articleNum));
        getContext().startActivity(HelpCenterIntents.intentForHelpCenterArticle(context, articleNum).toIntent());
    }

    private SpannableStringBuilder appendToBuilder(SpannableStringBuilder builder, CharSequence text, Object what, int flags) {
        int start = builder.length();
        builder.append(text);
        builder.setSpan(what, start, builder.length(), flags);
        return builder;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}

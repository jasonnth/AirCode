package com.airbnb.android.p011p3;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.controllers.ContactHostControllerProvider;
import com.airbnb.android.core.controllers.ContactHostFragmentController;
import com.airbnb.android.core.enums.ContactHostAction;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.p009p3.interfaces.ContactHostDataChangeListener;
import com.airbnb.android.core.presenters.GuestDetailsPresenter;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.AutoResizableButtonBar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.interfaces.Scrollable;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import icepick.State;

/* renamed from: com.airbnb.android.p3.ContactHostV2Fragment */
public class ContactHostV2Fragment extends P3BaseFragment implements ContactHostDataChangeListener {
    private static final String ARG_LISTING = "arg_listing";
    private static final String ARG_PRICING = "arg_pricing";
    @BindView
    AirTextView chinaTermsView;
    /* access modifiers changed from: private */
    public ContactHostFragmentController contactHostController;
    @BindView
    StandardRow datesRow;
    @BindView
    StandardRow guestsRow;
    @BindView
    HaloImageView hostPhoto;
    @State
    Listing listing;
    @BindView
    DocumentMarquee marquee;
    private PricingQuote pricingQuote;
    @BindView
    VerboseScrollView scrollView;
    @BindView
    FixedActionFooter stepThroughButton;
    @BindView
    AutoResizableButtonBar submitBar;
    @BindView
    AirToolbar toolbar;
    @BindView
    StandardRow yourMessageRow;

    public static ContactHostV2Fragment newInstance(Listing listing2) {
        return (ContactHostV2Fragment) ((FragmentBundleBuilder) FragmentBundler.make(new ContactHostV2Fragment()).putParcelable(ARG_LISTING, listing2)).build();
    }

    public void onAttach(Context context) {
        Check.argument(context instanceof ContactHostControllerProvider);
        super.onAttach(context);
        this.contactHostController = ((ContactHostControllerProvider) context).getContactHostFragmentController();
        Check.notNull(this.contactHostController);
    }

    public void onDetach() {
        this.contactHostController = null;
        super.onDetach();
    }

    public void onDestroyView() {
        this.contactHostController.unregisterListener(this);
        super.onDestroyView();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C7532R.layout.fragment_contact_host_v2, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.listing = (Listing) getArguments().getParcelable(ARG_LISTING);
        }
        setToolbar(this.toolbar);
        this.toolbar.scrollWith((Scrollable<?>) this.scrollView);
        setupSubmitBarButtons();
        setUpChinaTerms();
        this.contactHostController.registerListener(this);
        updateViewBasedOnNewPricingQuote(this.pricingQuote);
        refreshState();
        return view;
    }

    private void refreshState() {
        showHostBanner();
        refreshEachRowState();
        refreshButtonState();
    }

    private void showHostBanner() {
        this.marquee.setTitle(C7532R.string.contact_host_title);
        User host = this.listing.getPrimaryHost();
        if (host.hasProfilePic()) {
            this.hostPhoto.setImageUrl(host.getPictureUrl());
        }
        this.hostPhoto.setOnClickListener(ContactHostV2Fragment$$Lambda$1.lambdaFactory$(this, host));
        this.hostPhoto.setVisibility(0);
    }

    private void refreshEachRowState() {
        AirDate checkIn = this.contactHostController.getCheckInDate();
        AirDate checkOut = this.contactHostController.getCheckOutDate();
        configureRow(this.datesRow, getString(C7532R.string.dates), (checkIn == null || checkOut == null) ? "" : checkIn.getDateSpanString(getContext(), checkOut));
        GuestDetails guestDetails = this.contactHostController.getGuestDetails();
        String guestString = guestDetails != null ? GuestDetailsPresenter.formatGuestCountLabel(getContext(), guestDetails) : "";
        StandardRow standardRow = this.guestsRow;
        String string = getString(C7532R.string.guests);
        if (TextUtils.equals(guestString, getString(C7532R.string.guests))) {
            guestString = "";
        }
        configureRow(standardRow, string, guestString);
        configureRow(this.yourMessageRow, getString(C7532R.string.contact_host_input_title, this.listing.getPrimaryHost().getFirstName()), !hasMessage() ? getString(C7532R.string.contact_host_add_message_prompt) : this.contactHostController.getInquiryMessage());
    }

    private void refreshButtonState() {
        boolean readyToSend;
        boolean z = true;
        this.stepThroughButton.setButtonText(!hasDates() ? C0716R.string.enter_dates : C0716R.string.contact_host_add_message);
        if (!hasDates() || !hasMessage()) {
            readyToSend = false;
        } else {
            readyToSend = true;
        }
        ViewUtils.setInvisibleIf(this.stepThroughButton, readyToSend);
        AutoResizableButtonBar autoResizableButtonBar = this.submitBar;
        if (readyToSend) {
            z = false;
        }
        ViewUtils.setInvisibleIf(autoResizableButtonBar, z);
    }

    private void configureRow(StandardRow row, String title, String subTitle) {
        row.setActionText((CharSequence) getString(TextUtils.isEmpty(subTitle) ? C7532R.string.filter_add : C7532R.string.change));
        row.setTitle((CharSequence) title);
        row.setSubtitleText((CharSequence) subTitle);
    }

    private void updateViewBasedOnNewPricingQuote(PricingQuote pricingQuote2) {
        if (pricingQuote2 == null || pricingQuote2.isInstantBookable()) {
            this.submitBar.setLeftButtonText(C7532R.string.book);
            this.marquee.setCaption(C7532R.string.contact_host_ib_subtitle);
            return;
        }
        this.submitBar.setLeftButtonText(C7532R.string.request_to_book_rtb_cta);
        this.marquee.setCaption(C7532R.string.contact_host_rtb_subtitle);
    }

    private void setUpChinaTerms() {
        this.yourMessageRow.showDivider(shouldShowChinaTerms());
        String learnMore = getString(C7532R.string.contact_host_terms_html_link, getString(C7532R.string.radical_transparency_learn_more));
        ClickableLinkUtils.setupClickableTextView((TextView) this.chinaTermsView, getString(C7532R.string.contact_china_host_text_link, learnMore), ContactHostV2Fragment$$Lambda$2.lambdaFactory$(this), C7532R.color.canonical_press_darken, true);
        ViewUtils.setVisibleIf((View) this.chinaTermsView, shouldShowChinaTerms());
    }

    private boolean shouldShowChinaTerms() {
        return AirbnbConstants.COUNTRY_CODE_CHINA.equalsIgnoreCase(this.listing.getCountryCode()) && !ChinaUtils.isUserCountrySetToChina(this.mAccountManager.getCurrentUser());
    }

    private void setupSubmitBarButtons() {
        this.submitBar.setRightButtonText((CharSequence) getString(C7532R.string.contact_host_send));
        this.submitBar.setLeftButtonAction(new OnClickListener() {
            public void onClick(View v) {
                ContactHostV2Fragment.this.getActivity().startActivity(!ContactHostV2Fragment.this.hasMessage() ? P3BookingIntents.intentToBooking(ContactHostV2Fragment.this.controller, ContactHostV2Fragment.this.getActivity()) : P3BookingIntents.intentToBookingWithHostMessage(ContactHostV2Fragment.this.controller, ContactHostV2Fragment.this.getActivity(), ContactHostV2Fragment.this.contactHostController.getInquiryMessage()));
            }
        });
        this.submitBar.setRightButtonAction(new OnClickListener() {
            public void onClick(View v) {
                ContactHostV2Fragment.this.submitBar.setState(AutoResizableButtonBar.State.Loading);
                ContactHostV2Fragment.this.contactHostController.onSubmitToHost();
            }
        });
    }

    public void onActionCompleted(ContactHostAction action) {
        refreshState();
        if (action != ContactHostAction.MESSAGE && this.submitBar.getVisibility() == 0) {
            this.submitBar.setState(AutoResizableButtonBar.State.Loading);
        }
    }

    public void onPricingQuoteLoaded(PricingQuote pricingQuote2) {
        if (pricingQuote2 != null) {
            this.pricingQuote = pricingQuote2;
        }
        updateViewBasedOnNewPricingQuote(pricingQuote2);
        this.submitBar.setState(AutoResizableButtonBar.State.Normal);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ContactHost;
    }

    public boolean shouldShowAsDialog(Context context) {
        return false;
    }

    @OnClick
    public void showDatesFragment() {
        this.contactHostController.onDatesUpdateRequested();
    }

    @OnClick
    public void showGuestsFragment() {
        this.contactHostController.onGuestsUpdateRequested();
    }

    @OnClick
    public void showMessageComposeFragment() {
        this.contactHostController.onComposeMessageRequested();
    }

    @OnClick
    public void goToNextStep() {
        if (!hasDates()) {
            showDatesFragment();
        } else if (!hasMessage()) {
            showMessageComposeFragment();
        }
    }

    private boolean hasDates() {
        return (this.contactHostController.getCheckInDate() == null || this.contactHostController.getCheckOutDate() == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public boolean hasMessage() {
        return !TextUtils.isEmpty(this.contactHostController.getInquiryMessage());
    }
}

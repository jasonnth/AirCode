package com.airbnb.android.core.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.controllers.ContactHostControllerProvider;
import com.airbnb.android.core.controllers.ContactHostFragmentController;
import com.airbnb.android.core.enums.ContactHostAction;
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
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.interfaces.Scrollable;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import icepick.State;

public class ContactHostFragment extends AirDialogFragment implements ContactHostDataChangeListener {
    private static final String ARG_EDITABLE = "arg_editable";
    private static final String ARG_LISTING = "arg_listing";
    @BindView
    AirTextView chinaTermsView;
    private ContactHostFragmentController controller;
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
    @BindView
    VerboseScrollView scrollView;
    @BindView
    PrimaryButton sendButton;
    @BindView
    AirButton stepThroughButton;
    @BindView
    AirToolbar toolbar;
    @BindView
    StandardRow yourMessageRow;

    public static ContactHostFragment newInstance(Listing listing2) {
        return (ContactHostFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ContactHostFragment()).putBoolean(ARG_EDITABLE, true)).putParcelable(ARG_LISTING, listing2)).build();
    }

    public void onAttach(Context context) {
        Check.argument(context instanceof ContactHostControllerProvider);
        super.onAttach(context);
        this.controller = ((ContactHostControllerProvider) context).getContactHostFragmentController();
        Check.notNull(this.controller);
    }

    public void onDetach() {
        this.controller = null;
        super.onDetach();
    }

    public void onDestroyView() {
        this.controller.unregisterListener(this);
        super.onDestroyView();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0716R.layout.fragment_p3_contact_host, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        this.toolbar.scrollWith((Scrollable<?>) this.scrollView);
        if (savedInstanceState == null) {
            this.listing = (Listing) getArguments().getParcelable(ARG_LISTING);
        }
        setUpChinaTerms();
        this.controller.registerListener(this);
        refreshState();
        return view;
    }

    private void setUpChinaTerms() {
        this.yourMessageRow.showDivider(shouldShowChinaTerms());
        String learnMore = getString(C0716R.string.contact_host_terms_html_link, getString(C0716R.string.radical_transparency_learn_more));
        ClickableLinkUtils.setupClickableTextView((TextView) this.chinaTermsView, getString(C0716R.string.contact_china_host_text_link, learnMore), ContactHostFragment$$Lambda$1.lambdaFactory$(this), C0716R.color.canonical_press_darken, true);
        ViewUtils.setVisibleIf((View) this.chinaTermsView, shouldShowChinaTerms());
    }

    private boolean shouldShowChinaTerms() {
        return AirbnbConstants.COUNTRY_CODE_CHINA.equalsIgnoreCase(this.listing.getCountryCode()) && !ChinaUtils.isUserCountrySetToChina(this.mAccountManager.getCurrentUser());
    }

    private void refreshState() {
        showHostBanner();
        refreshDatesAndGuestsState();
        refreshMessageState();
        refreshButtonState();
    }

    private void refreshDatesAndGuestsState() {
        AirDate checkIn = this.controller.getCheckInDate();
        AirDate checkOut = this.controller.getCheckOutDate();
        configureInlineInputRow(this.datesRow, (checkIn == null || checkOut == null) ? getString(C0716R.string.dates) : checkIn.getDateSpanString(getContext(), checkOut));
        GuestDetails guestDetails = this.controller.getGuestDetails();
        configureInlineInputRow(this.guestsRow, guestDetails != null ? GuestDetailsPresenter.formatGuestCountLabel(getContext(), guestDetails) : getString(C0716R.string.guests));
    }

    private void refreshMessageState() {
        String inquiryMessage;
        StandardRow standardRow = this.yourMessageRow;
        if (!hasMessage()) {
            inquiryMessage = getString(C0716R.string.message_host_add_message_prompt);
        } else {
            inquiryMessage = this.controller.getInquiryMessage();
        }
        configureInputRow(standardRow, inquiryMessage);
    }

    private void refreshButtonState() {
        boolean readyToSend;
        boolean z = true;
        this.stepThroughButton.setText(!hasDates() ? C0716R.string.enter_dates : C0716R.string.type_your_message);
        if (!hasDates() || !hasMessage()) {
            readyToSend = false;
        } else {
            readyToSend = true;
        }
        ViewUtils.setInvisibleIf(this.stepThroughButton, readyToSend);
        PrimaryButton primaryButton = this.sendButton;
        if (readyToSend) {
            z = false;
        }
        ViewUtils.setInvisibleIf(primaryButton, z);
    }

    private void showHostBanner() {
        this.marquee.setTitle(C0716R.string.message_host);
        User host = this.listing.getPrimaryHost();
        this.marquee.setCaption((CharSequence) getString(C0716R.string.message_host_subtitle, this.listing.getRoomType(), host.getFirstName()));
        this.hostPhoto.setImageUrl(host.getPictureUrl());
        this.hostPhoto.setOnClickListener(ContactHostFragment$$Lambda$2.lambdaFactory$(this, host));
        this.hostPhoto.setVisibility(0);
    }

    private void configureInputRow(StandardRow row, String text) {
        row.setActionText((CharSequence) getResources().getString(TextUtils.isEmpty(text) ? C0716R.string.filter_add : C0716R.string.change));
        row.setSubtitleText((CharSequence) text);
    }

    private void configureInlineInputRow(StandardRow row, String title) {
        row.setActionText((CharSequence) getResources().getString(TextUtils.isEmpty(title) ? C0716R.string.filter_add : C0716R.string.edit));
        row.setTitle((CharSequence) title);
    }

    public void onActionCompleted(ContactHostAction action) {
        refreshState();
    }

    public void onPricingQuoteLoaded(PricingQuote pricingQuote) {
    }

    @OnClick
    public void showDatesFragment() {
        this.controller.onDatesUpdateRequested();
    }

    @OnClick
    public void showGuestsFragment() {
        this.controller.onGuestsUpdateRequested();
    }

    @OnClick
    public void showMessageComposeFragment() {
        this.controller.onComposeMessageRequested();
    }

    @OnClick
    public void contactHost() {
        this.sendButton.setLoading();
        this.controller.onSubmitToHost();
    }

    @OnClick
    public void goToNextStep() {
        if (!hasDates()) {
            showDatesFragment();
        } else if (!hasMessage()) {
            showMessageComposeFragment();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ContactHost;
    }

    public boolean shouldShowAsDialog(Context context) {
        return false;
    }

    private boolean hasDates() {
        return (this.controller.getCheckInDate() == null || this.controller.getCheckOutDate() == null) ? false : true;
    }

    private boolean hasMessage() {
        return !TextUtils.isEmpty(this.controller.getInquiryMessage());
    }
}

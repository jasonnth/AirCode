package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.CreateSpecialOfferRequest;
import com.airbnb.android.core.responses.SpecialOfferResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.EntryMarquee;
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class SpecialOfferFragment extends AirFragment {
    private static final String ARG_SK_CANCELLATION = "sk_cancellation_policy";
    @BindView
    StandardRow datesRow;
    @BindView
    StandardRow guestsRow;
    private boolean hostAgreedSouthKoreanPreapproval;
    /* access modifiers changed from: private */
    public Listener listener;
    @BindView
    StandardRow listingRow;
    @BindView
    EntryMarquee marquee;
    @BindView
    InlineInputRow priceRow;
    final RequestListener<SpecialOfferResponse> specialOfferRequestListener = new RequestListener<SpecialOfferResponse>() {
        public void onResponse(SpecialOfferResponse data) {
            SpecialOfferFragment.this.listener.goToSuccessfulSubmit();
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            NetworkUtil.tryShowErrorWithSnackbar(SpecialOfferFragment.this.submitButton, e);
            SpecialOfferFragment.this.submitButton.setState(State.Normal);
        }
    };
    @BindView
    AirButton submitButton;
    @BindView
    AirToolbar toolbar;

    public interface Listener {
        String getCurrency();

        AirDate getEndDate();

        String getFormattedPrice();

        int getInitialPrice();

        Listing getListing();

        int getNumGuests();

        AirDate getStartDate();

        long getThreadId();

        void goToChangeDatesFlow();

        void goToChangeListingFlow();

        void goToChangeNumGuestsFlow();

        void goToSuccessfulSubmit();
    }

    public static SpecialOfferFragment newInstance(boolean hostAgreedSouthKoreanPreapproval2) {
        return (SpecialOfferFragment) ((FragmentBundleBuilder) FragmentBundler.make(new SpecialOfferFragment()).putBoolean(ARG_SK_CANCELLATION, hostAgreedSouthKoreanPreapproval2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_special_offer, container, false);
        bindViews(view);
        Bundle args = getArguments();
        if (args == null || !args.containsKey(ARG_SK_CANCELLATION)) {
            this.hostAgreedSouthKoreanPreapproval = false;
        } else {
            this.hostAgreedSouthKoreanPreapproval = args.getBoolean(ARG_SK_CANCELLATION);
        }
        setListingRow();
        setDatesRow();
        setPriceRow();
        setGuestsRow();
        setToolbar(this.toolbar);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener interface");
        }
    }

    private void setListingRow() {
        this.listingRow.setSubtitleText((CharSequence) this.listener.getListing().getName());
        this.listingRow.setActionText(C0880R.string.change);
    }

    @OnClick
    public void onClickListingRow() {
        this.listener.goToChangeListingFlow();
    }

    private void setDatesRow() {
        String mdyFormat = getString(C0880R.string.mdy_short_with_full_year);
        this.datesRow.setSubtitleText((CharSequence) getString(C0880R.string.separator_with_values, this.listener.getStartDate().formatDate(mdyFormat), this.listener.getEndDate().formatDate(mdyFormat)));
        this.datesRow.setActionText(C0880R.string.change);
    }

    @OnClick
    public void onClickDatesRow() {
        this.listener.goToChangeDatesFlow();
    }

    private void setPriceRow() {
        this.priceRow.setTitle((CharSequence) getString(C0880R.string.special_offer_total, this.listener.getCurrency()));
        this.priceRow.setHint(this.listener.getFormattedPrice());
        this.priceRow.setInputType(2);
        this.priceRow.setRemoveHintOnFocus(true);
    }

    private void setGuestsRow() {
        this.guestsRow.setSubtitleText((CharSequence) getResources().getQuantityString(C0880R.plurals.x_guests, this.listener.getNumGuests(), new Object[]{Integer.valueOf(this.listener.getNumGuests())}));
        this.guestsRow.setActionText(C0880R.string.change);
    }

    private int getPrice() {
        String priceText = this.priceRow.getInputText();
        if (TextUtils.isEmpty(priceText)) {
            return this.listener.getInitialPrice();
        }
        return Integer.parseInt(priceText);
    }

    @OnClick
    public void onClickGuestsRow() {
        this.listener.goToChangeNumGuestsFlow();
    }

    @OnClick
    public void onSubmit() {
        this.submitButton.setState(State.Loading, ContextCompat.getColor(getContext(), C0880R.color.white));
        new CreateSpecialOfferRequest(this.listener.getStartDate(), this.listener.getNumGuests(), this.listener.getListing().getId(), this.listener.getStartDate().getDaysUntil(this.listener.getEndDate()), getPrice(), this.listener.getCurrency(), this.listener.getThreadId(), this.listener.getListing().isInstantBookEnabled(), this.hostAgreedSouthKoreanPreapproval).withListener((Observer) this.specialOfferRequestListener).execute(this.requestManager);
    }

    public Strap getNavigationTrackingParams() {
        return InboxType.Host.addLoggingParams(super.getNavigationTrackingParams()).mo11638kv("listing_id", this.listener.getListing().getId());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.SpecialOfferSheet;
    }
}

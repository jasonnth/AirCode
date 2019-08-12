package com.airbnb.android.lib.identity.psb;

import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.core.views.CountryCodeSelectionView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.P4FlowNavigationMethod.p172v1.C2466P4FlowNavigationMethod;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class IdentificationNationalityFragment extends BaseCreateIdentificationFragment implements SelectionSheetOnItemClickedListener<CountryCodeItem> {
    @BindView
    View bookingNextButton;
    @BindView
    CountryCodeSelectionView countrySelectionView;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    View nextButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_guest_select_country, container, false);
        bindViews(view);
        this.countrySelectionView.setSelectionSheetOnItemClickedListener(this);
        String countryCode = this.callback.getCountryCode();
        if (!TextUtils.isEmpty(countryCode)) {
            this.countrySelectionView.setSelectedCountryCode(countryCode);
        }
        setStyle(view);
        return view;
    }

    private void setStyle(View view) {
        GuestProfilesStyle style = GuestProfilesStyle.getStyle(this.callback.isP4Redesign());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        this.countrySelectionView.setStyle(style.selectionViewStyle);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf(this.nextButton, style.nextButtonVisible);
        ViewUtils.setVisibleIf(this.bookingNextButton, style.bookingNextButtonVisible);
    }

    public void onResume() {
        super.onResume();
        checkForValidInput();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onContinueClicked() {
        String countryCode = this.countrySelectionView.getSelectedCountryCode();
        if (countryCode != null) {
            if (this.callback.isP4Redesign()) {
                this.callback.bookingJitneyLogger.chinaGuestSelectNationality(this.callback.reservationDetails, this.callback.isInstantBookable, countryCode);
                this.callback.bookingJitneyLogger.clickNavigation(this.callback.reservationDetails, this.callback.isInstantBookable, C2467P4FlowPage.ChinaGuestNationality, C2466P4FlowNavigationMethod.NextButton);
            }
            this.callback.setCountryCode(countryCode);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextClicked() {
        this.callback.bookingJitneyLogger.clickNavigation(this.callback.reservationDetails, this.callback.isInstantBookable, C2467P4FlowPage.ChinaGuestNationality, C2466P4FlowNavigationMethod.NextButton);
        onContinueClicked();
    }

    public void onItemClicked(CountryCodeItem item) {
        checkForValidInput();
    }

    private void checkForValidInput() {
        boolean z;
        boolean z2 = true;
        View view = this.nextButton;
        if (!TextUtils.isEmpty(this.countrySelectionView.getSelectedCountryCode())) {
            z = true;
        } else {
            z = false;
        }
        view.setEnabled(z);
        View view2 = this.bookingNextButton;
        if (TextUtils.isEmpty(this.countrySelectionView.getSelectedCountryCode())) {
            z2 = false;
        }
        view2.setEnabled(z2);
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(this.callback.isP4Redesign());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ChinaGuestNationality;
    }
}

package com.airbnb.android.lib.identity.psb;

import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.interfaces.GuestIdentity.Type;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.P4FlowNavigationMethod.p172v1.C2466P4FlowNavigationMethod;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class SelectIdentificationTypeFragment extends BaseCreateIdentificationFragment implements SelectionSheetOnItemClickedListener<Type> {
    @BindView
    View bookingNextButton;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    View nextButton;
    @BindView
    IdentificationTypeSelectionView selectionView;

    public static SelectIdentificationTypeFragment newInstance() {
        return new SelectIdentificationTypeFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean haveIdType = false;
        View view = inflater.inflate(C0880R.layout.fragment_select_identification_type, container, false);
        ButterKnife.bind(this, view);
        this.selectionView.setSelectionSheetOnItemClickedListener(this);
        Type type = this.callback.getIdentificationType();
        if (type != null) {
            haveIdType = true;
        }
        if (haveIdType) {
            this.selectionView.setSelectedItem(type);
        }
        this.nextButton.setEnabled(haveIdType);
        this.bookingNextButton.setEnabled(haveIdType);
        setStyle(view);
        return view;
    }

    private void setStyle(View view) {
        GuestProfilesStyle style = GuestProfilesStyle.getStyle(this.callback.isP4Redesign());
        view.setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        this.selectionView.setStyle(style.selectionViewStyle);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf(this.nextButton, style.nextButtonVisible);
        ViewUtils.setVisibleIf(this.bookingNextButton, style.bookingNextButtonVisible);
    }

    public void onResume() {
        boolean z;
        boolean z2 = true;
        super.onResume();
        View view = this.nextButton;
        if (this.selectionView.getSelectedItem() != null) {
            z = true;
        } else {
            z = false;
        }
        view.setEnabled(z);
        View view2 = this.bookingNextButton;
        if (this.selectionView.getSelectedItem() == null) {
            z2 = false;
        }
        view2.setEnabled(z2);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextClick() {
        this.callback.setIdentificationType(this.selectionView.getSelectedItem());
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onBookingNextClick() {
        this.callback.bookingJitneyLogger.chinaGuestSelectIdType(this.callback.reservationDetails, this.callback.isInstantBookable, this.selectionView.getSelectedItem().getJitneyIdType());
        this.callback.bookingJitneyLogger.clickNavigation(this.callback.reservationDetails, this.callback.isInstantBookable, C2467P4FlowPage.ChinaGuestIdType, C2466P4FlowNavigationMethod.NextButton);
        onNextClick();
    }

    public void onItemClicked(Type item) {
        boolean z;
        boolean z2 = true;
        View view = this.nextButton;
        if (this.selectionView.getSelectedItem() != null) {
            z = true;
        } else {
            z = false;
        }
        view.setEnabled(z);
        View view2 = this.bookingNextButton;
        if (this.selectionView.getSelectedItem() == null) {
            z2 = false;
        }
        view2.setEnabled(z2);
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(this.callback.isP4Redesign());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ChinaGuestIdType;
    }
}

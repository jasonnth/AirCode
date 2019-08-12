package com.airbnb.android.wishlists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.presenters.GuestDetailsPresenter;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.components.TweenRow;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class WishListTweenFragment extends BaseWishListDetailsFragment {
    @BindView
    TweenRow datesRow;
    @BindView
    SimpleTextRow filtersReasonText;
    @BindView
    TweenRow guestsRow;
    @BindView
    AirToolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1758R.layout.fragment_wish_list_tween, container, false);
        bindViews(view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        setToolbar(this.toolbar);
        updateViews();
    }

    private void updateViews() {
        WishList wishList = getWishList();
        this.guestsRow.setInputText((CharSequence) GuestDetailsPresenter.formatGuestCountLabel(getContext(), wishList.getGuestDetails()));
        if (wishList.hasDates()) {
            this.datesRow.setInputText((CharSequence) wishList.getCheckin().getDateSpanString(getContext(), wishList.getCheckout()));
        } else {
            this.datesRow.setInputText(C1758R.string.filter_change);
        }
        ViewLibUtils.setVisibleIf(this.filtersReasonText, Experiments.showImprovedWishListFiltersUx());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C1758R.C1760id.reset_all) {
            return super.onOptionsItemSelected(item);
        }
        this.parentFragment.onFiltersCleared();
        return true;
    }

    public void onWishListChanged() {
        updateViews();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSaveClicked() {
        this.parentFragment.onTweenSaveClicked();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onDatesClicked() {
        this.parentFragment.onDatesClicked();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onGuestsClicked() {
        this.parentFragment.onGuestsClicked();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.WishListFilters;
    }
}

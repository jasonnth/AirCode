package com.airbnb.android.core.fragments.guestpicker;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.interfaces.UpdateRequestListener;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.views.guestpicker.GuestsPickerSheetWithButtonView;
import com.airbnb.android.core.views.guestpicker.GuestsPickerSheetWithButtonView.Listener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.FragmentUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;

public class GuestPickerFragment extends AirFragment implements UpdateRequestListener, Listener {
    private static final String ARG_ANIMATE_RECT = "arg_animate_rect";
    private static final String ARG_GUESTS_ONLY = "arg_guests_only";
    private static final String ARG_GUEST_DATA = "arg_guest_data";
    private static final String ARG_LISTING = "arg_listing";
    private static final String ARG_MAX_NUM_GUESTS = "arg_max_num_guests";
    private static final String ARG_MIN_NUM_ADULTS = "arg_min_num_guests";
    private static final String ARG_SHOW_BLOCK_IB_WARNING = "arg_show_block_ib_warning";
    private static final String ARG_SHOW_MAX_GUESTS = "arg_show_max_guests_description";
    private static final String ARG_SOURCE_TAG = "arg_source_tag";
    private static final String ARG_STYLE_CHINA = "arg_style_china";
    BottomBarController bottomBarController;
    private GuestPickerController controller;
    @BindView
    GuestsPickerSheetWithButtonView guestsPickerView;
    @State
    boolean isChinaStyle;
    @BindView
    JellyfishView jellyfishView;
    private Snackbar snackbar;
    @BindView
    AirToolbar toolbar;

    public interface GuestPickerController {
        NavigationTag getNavigationAnalyticsTag();

        void onGuestDetailsSaved(GuestDetails guestDetails, UpdateRequestListener updateRequestListener);
    }

    public interface GuestPickerControllerProvider {
        GuestPickerController getGuestPickerController();
    }

    public static class GuestPickerFragmentBuilder {
        private Rect animateRect;
        private boolean forTotalGuestsOnly;
        private final GuestDetails guestDetails;
        private boolean isChinaStyle = false;
        private Listing listing;
        private int maxNumberOfGuests = 16;
        private int minNumberOfAdults = GuestDetails.minNumAdults();
        private boolean showBlockInstantBookWarning = false;
        private boolean showMaxGuestsDescription = true;
        private final String sourceTag;

        public GuestPickerFragmentBuilder(GuestDetails guestDetails2, String sourceTag2) {
            this.guestDetails = guestDetails2;
            this.sourceTag = sourceTag2;
        }

        public GuestPickerFragmentBuilder setListing(Listing listing2) {
            this.listing = listing2;
            return this;
        }

        public GuestPickerFragmentBuilder showMaxGuestsDescription(boolean showMaxGuestsDescription2) {
            this.showMaxGuestsDescription = showMaxGuestsDescription2;
            return this;
        }

        public GuestPickerFragmentBuilder setAnimateRect(Rect animateRect2) {
            this.animateRect = animateRect2;
            return this;
        }

        public GuestPickerFragmentBuilder setMaxNumberOfGuests(int maxNumberOfGuests2) {
            this.maxNumberOfGuests = maxNumberOfGuests2;
            return this;
        }

        public GuestPickerFragmentBuilder setMinNumberOfAdults(int minNumberOfAdults2) {
            this.minNumberOfAdults = minNumberOfAdults2;
            return this;
        }

        public GuestPickerFragmentBuilder setShowBlockInstantBookWarning(boolean showBlockInstantBookWarning2) {
            this.showBlockInstantBookWarning = showBlockInstantBookWarning2;
            return this;
        }

        public GuestPickerFragmentBuilder forTotalNumberOfGuests(boolean forTotalGuestsOnly2) {
            this.forTotalGuestsOnly = forTotalGuestsOnly2;
            return this;
        }

        public GuestPickerFragmentBuilder setChinaStyle(boolean isChinaStyle2) {
            this.isChinaStyle = isChinaStyle2;
            return this;
        }

        public GuestPickerFragment build() {
            return (GuestPickerFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new GuestPickerFragment()).putString(GuestPickerFragment.ARG_SOURCE_TAG, this.sourceTag)).putParcelable(GuestPickerFragment.ARG_GUEST_DATA, this.guestDetails)).putParcelable(GuestPickerFragment.ARG_LISTING, this.listing)).putBoolean(GuestPickerFragment.ARG_GUESTS_ONLY, this.forTotalGuestsOnly)).putBoolean(GuestPickerFragment.ARG_SHOW_BLOCK_IB_WARNING, this.showBlockInstantBookWarning)).putBoolean(GuestPickerFragment.ARG_SHOW_MAX_GUESTS, this.showMaxGuestsDescription)).putBoolean(GuestPickerFragment.ARG_STYLE_CHINA, this.isChinaStyle)).putInt(GuestPickerFragment.ARG_MAX_NUM_GUESTS, this.maxNumberOfGuests)).putInt(GuestPickerFragment.ARG_MIN_NUM_ADULTS, this.minNumberOfAdults)).putParcelable(GuestPickerFragment.ARG_ANIMATE_RECT, this.animateRect)).build();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        if (savedInstanceState == null) {
            this.isChinaStyle = getArguments().getBoolean(ARG_STYLE_CHINA, false);
        }
        ViewGroup view = (ViewGroup) inflater.inflate(this.isChinaStyle ? C0716R.layout.fragment_guest_picker_china : C0716R.layout.fragment_guest_picker, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.guestsPickerView.setGuestData((GuestDetails) getArguments().getParcelable(ARG_GUEST_DATA));
        }
        this.guestsPickerView.setGuestsPickerListener(this);
        this.guestsPickerView.setMaxGuestsCount(getArguments().getInt(ARG_MAX_NUM_GUESTS));
        this.guestsPickerView.setMinNumberAdults(getArguments().getInt(ARG_MIN_NUM_ADULTS));
        this.guestsPickerView.shouldShowMaxGuestDescription(getArguments().getBoolean(ARG_SHOW_MAX_GUESTS));
        this.guestsPickerView.setShowBlockInstantBookWarning(getArguments().getBoolean(ARG_SHOW_BLOCK_IB_WARNING, false));
        Listing listing = (Listing) getArguments().getParcelable(ARG_LISTING);
        if (!(listing == null || listing.getGuestControls() == null)) {
            this.guestsPickerView.setGuestControls(listing.getGuestControls());
        }
        boolean forGuestsOnly = getArguments().getBoolean(ARG_GUESTS_ONLY, false);
        GuestsPickerSheetWithButtonView guestsPickerSheetWithButtonView = this.guestsPickerView;
        if (!forGuestsOnly) {
            z = true;
        } else {
            z = false;
        }
        guestsPickerSheetWithButtonView.showExpandedGuestsPicker(z);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((CoreGraph) CoreApplication.instance().component()).inject(this);
        if (getActivity() instanceof GuestPickerControllerProvider) {
            this.controller = ((GuestPickerControllerProvider) getActivity()).getGuestPickerController();
        } else if (getParentFragment() instanceof GuestPickerControllerProvider) {
            this.controller = ((GuestPickerControllerProvider) getParentFragment()).getGuestPickerController();
        }
        Check.notNull(this.controller);
    }

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Rect animateRect = (Rect) getArguments().getParcelable(ARG_ANIMATE_RECT);
        if (animateRect != null) {
            return FragmentUtils.animateSheetExpansion(this, enter, animateRect);
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    public void onStart() {
        super.onStart();
        this.bottomBarController.setShowBottomBar(false, true);
    }

    public void onDestroyView() {
        this.guestsPickerView.setGuestsPickerListener(null);
        super.onDestroyView();
    }

    public void onDestroy() {
        this.controller = null;
        super.onDestroy();
    }

    public void onSaveButtonClicked() {
        this.controller.onGuestDetailsSaved(this.guestsPickerView.getGuestData(), this);
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.controller.getNavigationAnalyticsTag();
    }

    public void onPause() {
        if (this.snackbar != null && this.snackbar.isShownOrQueued()) {
            this.snackbar.dismiss();
            this.snackbar = null;
        }
        this.guestsPickerView.dismissAllSnackBars();
        super.onPause();
    }

    public void onUpdateError(String errorMessage) {
        this.guestsPickerView.showLoadingState(false);
        this.snackbar = new SnackbarWrapper().view(this.guestsPickerView).body(errorMessage).duration(-1).buildAndShow();
    }

    public void onUpdateStarted() {
        this.guestsPickerView.showLoadingState(true);
    }

    public void onUpdated() {
        this.guestsPickerView.showSuccessState();
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11637kv(FindTweenAnalytics.GUESTS, this.guestsPickerView.getNumberAdults()).mo11640kv(FindTweenAnalytics.PETS, this.guestsPickerView.hasPets()).mo11639kv(BaseAnalytics.FROM, getArguments().getString(ARG_SOURCE_TAG));
    }
}

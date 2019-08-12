package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;

public class ModalSingleCalendarFragment extends SingleCalendarFragment {
    private OnBackListener customOnBackListener;
    private boolean resetActivityBackListenerWithCustomListener;

    public static ModalSingleCalendarFragment newInstance(long listingId, String listingName) {
        return (ModalSingleCalendarFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ModalSingleCalendarFragment()).putLong("listing_id", listingId)).putString("listing_name", listingName)).putBoolean("nav_from_multical", false)).build();
    }

    public void setCustomOnBackListener(OnBackListener listener) {
        setCustomOnBackListener(listener, true);
    }

    public void setCustomOnBackListener(OnBackListener listener, boolean resetActivityBackListener) {
        this.customOnBackListener = listener;
        this.resetActivityBackListenerWithCustomListener = resetActivityBackListener;
    }

    /* access modifiers changed from: protected */
    public void updateToolbar(int numDaysSelected) {
        super.updateToolbar(numDaysSelected);
        this.toolbar.setNavigationOnClickListener(ModalSingleCalendarFragment$$Lambda$1.lambdaFactory$(this));
    }

    public void onPause() {
        super.onPause();
        if (this.resetActivityBackListenerWithCustomListener) {
            ((AirActivity) getActivity()).setOnBackPressedListener(this.customOnBackListener);
        }
    }

    public boolean onBackPressed() {
        boolean didClear = super.onBackPressed();
        if (didClear || this.customOnBackListener == null) {
            return didClear;
        }
        return this.customOnBackListener.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public int getNavigationIcon(boolean editMode) {
        return 2;
    }
}

package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.HostReservationObjectActivity;
import com.airbnb.android.lib.adapters.GuestStarRatingsEpoxyController;
import com.airbnb.android.lib.adapters.GuestStarRatingsEpoxyController.GuestStarRatingsListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;

public class GuestStarRatingsFragment extends AirFragment {
    private static String ARG_GUEST = TripRole.ROLE_KEY_GUEST;
    private GuestStarRatingsListener actionListener;
    private GuestStarRatingsEpoxyController epoxyController;
    private User guest;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static GuestStarRatingsFragment newInstance(User guest2) {
        Check.notNull(guest2);
        Check.notNull(guest2.getReviewRatingsAsGuest());
        return (GuestStarRatingsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new GuestStarRatingsFragment()).putParcelable(ARG_GUEST, guest2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.guest = (User) getArguments().getParcelable(ARG_GUEST);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.recyclerview_with_toolbar_dark, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.epoxyController = new GuestStarRatingsEpoxyController(getContext(), this.guest, this.actionListener);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Check.state(context instanceof HostReservationObjectActivity);
        this.actionListener = GuestStarRatingsFragment$$Lambda$1.lambdaFactory$(this, context);
    }

    public void onDetach() {
        super.onDetach();
        this.actionListener = null;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.HostReservationGuestReviewRatings;
    }
}

package com.airbnb.android.itinerary.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.epoxyControllers.FlightReservationObjectController;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.collections.AirRecyclerView;

public class FlightReservationObjectFragment extends AirFragment {
    public static final String CONFIRMATION_CODE = "confirmation_code";
    public static final String PICTURE = "picture";
    public static final String RESERVATION_ID = "reservation_id";
    private EpoxyController controller;
    @BindView
    AirRecyclerView recyclerView;

    public static FlightReservationObjectFragment newInstance(Long id, String confirmationCode, String picture) {
        return (FlightReservationObjectFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new FlightReservationObjectFragment()).putLong("reservation_id", id.longValue())).putString("confirmation_code", confirmationCode)).putString("picture", picture)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5755R.layout.fragment_recycler_view_only, container, false);
        bindViews(view);
        this.controller = new FlightReservationObjectController();
        this.recyclerView.setEpoxyController(this.controller);
        this.recyclerView.setHasFixedSize(true);
        this.controller.requestModelBuild();
        return view;
    }

    public void onDestroyView() {
        this.recyclerView.setAdapter(null);
        super.onDestroyView();
    }
}

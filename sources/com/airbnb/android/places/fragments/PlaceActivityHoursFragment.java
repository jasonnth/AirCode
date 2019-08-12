package com.airbnb.android.places.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.adapters.PlaceActivityHoursController;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;

public class PlaceActivityHoursFragment extends BasePlaceActivityFragment {
    private static final String ARG_PLACE = "place";
    private PlaceActivityHoursController controller;
    private Place place;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static PlaceActivityHoursFragment newInstance(Place place2) {
        return (PlaceActivityHoursFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PlaceActivityHoursFragment()).putParcelable(ARG_PLACE, place2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.place = (Place) getArguments().getParcelable(ARG_PLACE);
        Check.notNull(this.place);
        View view = inflater.inflate(C7627R.layout.recyclerview_with_toolbar, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.toolbar.setTheme(3);
        this.controller = new PlaceActivityHoursController();
        this.controller.setData(this.place);
        this.recyclerView.setAdapter(this.controller.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        return view;
    }
}

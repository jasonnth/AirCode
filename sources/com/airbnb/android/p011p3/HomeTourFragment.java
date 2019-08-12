package com.airbnb.android.p011p3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InfiniteDotIndicator;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.android.p3.HomeTourFragment */
public class HomeTourFragment extends HomeTourBaseFragment {
    private static final String ARG_POSITION = "position";
    @BindView
    Carousel carousel;
    @BindView
    InfiniteDotIndicator dotIndicator;
    @BindView
    AirButton showAllRoomsButton;
    @BindView
    AirToolbar toolbar;

    public static HomeTourFragment newInstance(int position) {
        return (HomeTourFragment) ((FragmentBundleBuilder) FragmentBundler.make(new HomeTourFragment()).putInt("position", position)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7532R.layout.fragment_home_tour, container, false);
        bindViews(view);
        setHasOptionsMenu(true);
        setToolbar(this.toolbar);
        this.showAllRoomsButton.setOnClickListener(HomeTourFragment$$Lambda$1.lambdaFactory$(this));
        int initialPosition = getArguments().getInt("position");
        this.carousel.setEpoxyControllerAndBuildModels(new HomeTourEpoxyController(getContext(), this.controller));
        this.carousel.setHasFixedSize(true);
        this.carousel.scrollToPosition(initialPosition);
        this.dotIndicator.setRecyclerView(this.carousel);
        return view;
    }
}

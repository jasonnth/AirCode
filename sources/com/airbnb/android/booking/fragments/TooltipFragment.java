package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.EntryMarquee;
import com.airbnb.p027n2.components.TextRow;

public class TooltipFragment extends AirFragment {
    private static final String ARG_BODY = "body";
    private static final String ARG_TITLE = "title";
    @BindView
    AirToolbar toolbar;
    @BindView
    EntryMarquee tooltipMarquee;
    @BindView
    TextRow tooltipTextRow;

    public static TooltipFragment newInstance(String title, String body) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("body", body);
        TooltipFragment fragment = new TooltipFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_tooltip, container, false);
        bindViews(view);
        getAirActivity().setSupportActionBar(this.toolbar);
        String title = getArguments().getString("title");
        String body = getArguments().getString("body");
        this.tooltipMarquee.setTitle((CharSequence) title);
        this.tooltipTextRow.setText((CharSequence) body);
        return view;
    }
}

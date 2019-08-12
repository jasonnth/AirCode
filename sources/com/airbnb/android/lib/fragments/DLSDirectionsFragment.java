package com.airbnb.android.lib.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.MapUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SimpleTextRow;
import icepick.State;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DLSDirectionsFragment extends AirFragment {
    private static final String ARG_LISTING = "listing";
    @State
    Listing listing;
    @BindView
    SimpleTextRow textRow;
    @BindView
    AirToolbar toolbar;

    public static DLSDirectionsFragment newInstance(Listing listing2) {
        return (DLSDirectionsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new DLSDirectionsFragment()).putParcelable("listing", listing2)).build();
    }

    public static Intent getMapIntent(Activity activity, Listing listing2) {
        String url;
        String url2 = "http://maps.google.com/maps?q=";
        try {
            url = url2 + URLEncoder.encode(listing2.getAddress(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            url = url2 + "loc:" + listing2.getLatitude() + "+" + listing2.getLongitude();
        }
        return MapUtil.getMapIntent(activity, url);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.listing = (Listing) getArguments().getParcelable("listing");
        }
        Check.notNull(this.listing);
        View view = inflater.inflate(C0880R.layout.fragment_dls_directions, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.textRow.setText((CharSequence) this.listing.getDirections());
        return view;
    }

    @OnClick
    public void launchMap() {
        Intent intent = getMapIntent(getActivity(), this.listing);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), C0880R.string.no_maps, 0).show();
        }
    }
}

package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SimpleTextRow;
import icepick.State;

public class KonaHouseManualFragment extends AirDialogFragment {
    private static final String ARG_HOUSE_MANUAL = "house_manual";
    @State
    String houseManual;
    @BindView
    SimpleTextRow houseManualTextRow;
    @BindView
    AirToolbar toolbar;

    public static KonaHouseManualFragment newInstance(String houseManual2) {
        return (KonaHouseManualFragment) ((FragmentBundleBuilder) FragmentBundler.make(new KonaHouseManualFragment()).putString("house_manual", houseManual2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_kona_house_manual, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        if (getAirActivity() instanceof TransparentActionBarActivity) {
            ((TransparentActionBarActivity) getAirActivity()).getAirToolbar().setVisibility(8);
        }
        if (this.houseManual == null) {
            this.houseManual = getArguments().getString("house_manual");
        }
        this.houseManualTextRow.setText((CharSequence) this.houseManual);
        return view;
    }
}

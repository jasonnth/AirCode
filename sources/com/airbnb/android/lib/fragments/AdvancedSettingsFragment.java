package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.net.BandwidthMode;
import com.airbnb.android.core.net.LowBandwidthManager;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.settings.AdvancedSettingsEpoxyController;
import com.airbnb.android.lib.adapters.settings.AdvancedSettingsEpoxyController.Listener;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.p027n2.components.AirToolbar;
import java.util.ArrayList;

public class AdvancedSettingsFragment extends AirFragment {
    private static final int REQUEST_CODE_BANDWIDTH_MODE = 100;
    private static final String TAG_DIALOG = "dialog";
    private AdvancedSettingsEpoxyController epoxyController;
    private Listener listener = new Listener() {
        public void onFontOverrideSettingsClicked(boolean isChecked) {
            AdvancedSettingsFragment.this.mPreferences.getSharedPreferences().edit().putBoolean(AirbnbConstants.PREFS_FONT_OVERRIDE, isChecked).apply();
            Toast.makeText(AdvancedSettingsFragment.this.getContext(), C0880R.string.force_system_fonts_change, 0).show();
        }

        public void onBandwidthModeChanged() {
            ArrayList<String> itemsList = new ArrayList<>();
            for (BandwidthMode mode : BandwidthMode.values()) {
                itemsList.add(AdvancedSettingsFragment.this.getString(mode.mTitleRes));
            }
            ZenDialog dialog = RadioButtonListZenDialogFragment.newInstance(C0880R.string.title_bandwidth_mode_selector, itemsList, AdvancedSettingsFragment.this.lowBandwidthManager.getBandwidthMode().ordinal());
            dialog.setTargetFragment(AdvancedSettingsFragment.this, 100);
            dialog.show(AdvancedSettingsFragment.this.getFragmentManager(), AdvancedSettingsFragment.TAG_DIALOG);
        }

        public void onBandwidthModeLongClicked() {
            ZenDialog.createSingleButtonDialog(C0880R.string.bandwidth_mode, C0880R.string.force_low_bandwidth_tooltip, C0880R.string.okay).show(AdvancedSettingsFragment.this.getFragmentManager(), (String) null);
        }
    };
    LowBandwidthManager lowBandwidthManager;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static Fragment newInstance() {
        return new AdvancedSettingsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_recycler_view_with_toolbar, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        setToolbar(this.toolbar);
        this.toolbar.setTitle(C0880R.string.advanced_settings);
        this.epoxyController = new AdvancedSettingsEpoxyController(getContext(), this.mPreferences, this.listener, this.lowBandwidthManager.getBandwidthMode().mTitleRes);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 100:
                if (resultCode == -1 && data != null) {
                    int selectedItem = data.getIntExtra(RadioButtonListZenDialogFragment.EXTRA_SELECTED_ITEM, 0);
                    this.lowBandwidthManager.setBandwidthMode(selectedItem);
                    this.epoxyController.updateBandwithModeTitle(BandwidthMode.getBandwidthModeFromKey(selectedItem).mTitleRes);
                    break;
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}

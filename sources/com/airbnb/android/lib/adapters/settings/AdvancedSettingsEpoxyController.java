package com.airbnb.android.lib.adapters.settings;

import android.content.Context;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.utils.ShakeFeedbackSensorListener;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class AdvancedSettingsEpoxyController extends AirEpoxyController {
    StandardRowEpoxyModel_ bandWidthModeChangedRow;
    private int bandwidthModeTitleRes;
    SwitchRowEpoxyModel_ fontOverrideSettingsRow;
    private Listener listener;
    private AirbnbPreferences preferences;
    ShakeFeedbackSensorListener shakeFeedbackSensorListener;
    SwitchRowEpoxyModel_ shakeToSendFeedbackClickedRow;
    ToolbarSpacerEpoxyModel_ spacerRow;

    public interface Listener {
        void onBandwidthModeChanged();

        void onBandwidthModeLongClicked();

        void onFontOverrideSettingsClicked(boolean z);
    }

    public AdvancedSettingsEpoxyController(Context context, AirbnbPreferences preferences2, Listener listener2, int bandwidthModeTitleRes2) {
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
        this.preferences = preferences2;
        this.listener = listener2;
        this.bandwidthModeTitleRes = bandwidthModeTitleRes2;
        requestModelBuild();
    }

    public void updateBandwithModeTitle(int bandwidthModeTitleRes2) {
        this.bandwidthModeTitleRes = bandwidthModeTitleRes2;
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        setupSpacerRow();
        setupFontOverrideSettingsRow();
        setupBandWidthModeChangeRow();
        setupShakeToSendFeedbackRow();
    }

    private void setupSpacerRow() {
        this.spacerRow.addTo(this);
    }

    private void setupFontOverrideSettingsRow() {
        this.fontOverrideSettingsRow.titleRes(C0880R.string.override_font).checked(this.preferences.getSharedPreferences().getBoolean(AirbnbConstants.PREFS_FONT_OVERRIDE, false)).checkedChangeListener(AdvancedSettingsEpoxyController$$Lambda$1.lambdaFactory$(this)).style(SwitchStyle.Filled).addTo(this);
    }

    private void setupBandWidthModeChangeRow() {
        this.bandWidthModeChangedRow.titleRes(C0880R.string.bandwidth_mode).actionText(this.bandwidthModeTitleRes).clickListener(AdvancedSettingsEpoxyController$$Lambda$2.lambdaFactory$(this)).longClickListener(AdvancedSettingsEpoxyController$$Lambda$3.lambdaFactory$(this)).addTo(this);
    }

    private void setupShakeToSendFeedbackRow() {
        this.shakeToSendFeedbackClickedRow.titleRes(C0880R.string.shake_title).checked(this.shakeFeedbackSensorListener.isEnabled()).checkedChangeListener(AdvancedSettingsEpoxyController$$Lambda$4.lambdaFactory$(this)).style(SwitchStyle.Filled).addTo(this);
    }
}

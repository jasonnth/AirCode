package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.intents.PlacesIntents;
import com.airbnb.android.core.viewcomponents.models.InputMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class DebugActivityPDPActivity extends AirActivity {
    @BindView
    RecyclerView recyclerView;

    class DebugActivityPDPController extends AirEpoxyController {
        DebugActivityPDPController() {
        }

        /* access modifiers changed from: protected */
        public void buildModels() {
            new InputMarqueeEpoxyModel_().forSearch(true).hint(C0880R.string.search).editorActionListener(DebugActivityPDPActivity$DebugActivityPDPController$$Lambda$1.lambdaFactory$(DebugActivityPDPActivity.this)).m4930id(Long.MAX_VALUE).addTo(this);
            for (int i = 10; i < 250; i++) {
                int finalI = i;
                new StandardRowEpoxyModel_().title((CharSequence) String.valueOf(finalI)).clickListener(DebugActivityPDPActivity$DebugActivityPDPController$$Lambda$2.lambdaFactory$(this, finalI)).m5602id((long) finalI).addTo(this);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_debug_activity_pdp);
        ButterKnife.bind((Activity) this);
        DebugActivityPDPController controller = new DebugActivityPDPController();
        this.recyclerView.setAdapter(controller.getAdapter());
        controller.requestModelBuild();
    }

    /* access modifiers changed from: private */
    public void goToPlaceActivity(long i) {
        startActivity(PlacesIntents.intentForPlaceActivityPDP((Context) this, i));
    }

    /* access modifiers changed from: 0000 */
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        String searchQuery = v.getText().toString().trim();
        if (!KeyboardUtils.isEnterOrSearch(actionId, event) || TextUtils.isEmpty(searchQuery)) {
            return false;
        }
        KeyboardUtils.dismissSoftKeyboard((View) v);
        try {
            goToPlaceActivity(Long.valueOf(searchQuery).longValue());
            return true;
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", 0).show();
            return false;
        }
    }
}

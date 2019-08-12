package com.airbnb.android.lib.utils.erf;

import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.StickyButton;

public class ErfOverrideActivity_ViewBinding implements Unbinder {
    private ErfOverrideActivity target;
    private View view2131755412;
    private TextWatcher view2131755412TextWatcher;

    public ErfOverrideActivity_ViewBinding(ErfOverrideActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public ErfOverrideActivity_ViewBinding(final ErfOverrideActivity target2, View source) {
        this.target = target2;
        target2.mRefreshExperiments = (StickyButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.button_refresh, "field 'mRefreshExperiments'", StickyButton.class);
        target2.mExperimentList = (ListView) Utils.findRequiredViewAsType(source, C0880R.C0882id.experiment_list, "field 'mExperimentList'", ListView.class);
        target2.mFab = (FloatingActionButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.button_add_experiment, "field 'mFab'", FloatingActionButton.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.erf_filter_text, "field 'erfFilter' and method 'filterExperiments'");
        target2.erfFilter = (EditText) Utils.castView(view, C0880R.C0882id.erf_filter_text, "field 'erfFilter'", EditText.class);
        this.view2131755412 = view;
        this.view2131755412TextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                target2.filterExperiments(p0);
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
            }
        };
        ((TextView) view).addTextChangedListener(this.view2131755412TextWatcher);
    }

    public void unbind() {
        ErfOverrideActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mRefreshExperiments = null;
        target2.mExperimentList = null;
        target2.mFab = null;
        target2.erfFilter = null;
        ((TextView) this.view2131755412).removeTextChangedListener(this.view2131755412TextWatcher);
        this.view2131755412TextWatcher = null;
        this.view2131755412 = null;
    }
}

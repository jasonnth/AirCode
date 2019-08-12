package com.airbnb.android.lib.activities;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class RequestTestSuiteActivity_ViewBinding implements Unbinder {
    private RequestTestSuiteActivity target;
    private View view2131755385;
    private View view2131755386;
    private View view2131755387;

    public RequestTestSuiteActivity_ViewBinding(RequestTestSuiteActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public RequestTestSuiteActivity_ViewBinding(final RequestTestSuiteActivity target2, View source) {
        this.target = target2;
        target2.txtOutput = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txtOutput, "field 'txtOutput'", TextView.class);
        target2.txtSoftTTL = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txtSoftTTL, "field 'txtSoftTTL'", TextView.class);
        target2.txtTTL = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txtTTL, "field 'txtTTL'", TextView.class);
        target2.scrollView = (ScrollView) Utils.findRequiredViewAsType(source, C0880R.C0882id.scroll_view, "field 'scrollView'", ScrollView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.checkDouble, "field 'checkDouble' and method 'onClickDouble'");
        target2.checkDouble = (CheckBox) Utils.castView(view, C0880R.C0882id.checkDouble, "field 'checkDouble'", CheckBox.class);
        this.view2131755387 = view;
        ((CompoundButton) view).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton p0, boolean p1) {
                target2.onClickDouble();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.btnExecute, "method 'onClickExecute'");
        this.view2131755385 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickExecute();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.btnClear, "method 'onClickClearLogs'");
        this.view2131755386 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickClearLogs();
            }
        });
    }

    public void unbind() {
        RequestTestSuiteActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.txtOutput = null;
        target2.txtSoftTTL = null;
        target2.txtTTL = null;
        target2.scrollView = null;
        target2.checkDouble = null;
        ((CompoundButton) this.view2131755387).setOnCheckedChangeListener(null);
        this.view2131755387 = null;
        this.view2131755385.setOnClickListener(null);
        this.view2131755385 = null;
        this.view2131755386.setOnClickListener(null);
        this.view2131755386 = null;
    }
}

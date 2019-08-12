package com.airbnb.android.lib.fragments;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class ShakeFeedbackDialog_ViewBinding implements Unbinder {
    private ShakeFeedbackDialog target;
    private View view2131757658;
    private View view2131757659;
    private TextWatcher view2131757659TextWatcher;

    public ShakeFeedbackDialog_ViewBinding(final ShakeFeedbackDialog target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.feedback_type, "field 'feedbackType' and method 'onFeedbackTypeChanged'");
        target2.feedbackType = (Spinner) Utils.castView(view, C0880R.C0882id.feedback_type, "field 'feedbackType'", Spinner.class);
        this.view2131757658 = view;
        ((AdapterView) view).setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View p1, int p2, long p3) {
                target2.onFeedbackTypeChanged();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.feedback_description, "field 'description' and method 'onDescriptionChanged'");
        target2.description = (EditText) Utils.castView(view2, C0880R.C0882id.feedback_description, "field 'description'", EditText.class);
        this.view2131757659 = view2;
        this.view2131757659TextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                target2.onDescriptionChanged();
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
            }
        };
        ((TextView) view2).addTextChangedListener(this.view2131757659TextWatcher);
        target2.positiveButton = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.positive_button, "field 'positiveButton'", TextView.class);
    }

    public void unbind() {
        ShakeFeedbackDialog target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.feedbackType = null;
        target2.description = null;
        target2.positiveButton = null;
        ((AdapterView) this.view2131757658).setOnItemSelectedListener(null);
        this.view2131757658 = null;
        ((TextView) this.view2131757659).removeTextChangedListener(this.view2131757659TextWatcher);
        this.view2131757659TextWatcher = null;
        this.view2131757659 = null;
    }
}

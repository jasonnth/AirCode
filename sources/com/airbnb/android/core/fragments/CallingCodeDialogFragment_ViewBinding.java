package com.airbnb.android.core.fragments;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;

public class CallingCodeDialogFragment_ViewBinding implements Unbinder {
    private CallingCodeDialogFragment target;
    private View view2131755525;
    private TextWatcher view2131755525TextWatcher;

    public CallingCodeDialogFragment_ViewBinding(final CallingCodeDialogFragment target2, View source) {
        this.target = target2;
        target2.listView = (ListView) Utils.findRequiredViewAsType(source, C0716R.C0718id.calling_code_listView, "field 'listView'", ListView.class);
        View view = Utils.findRequiredView(source, C0716R.C0718id.search_calling_code_editText, "method 'updateSearch'");
        this.view2131755525 = view;
        this.view2131755525TextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                target2.updateSearch(p0);
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
            }
        };
        ((TextView) view).addTextChangedListener(this.view2131755525TextWatcher);
    }

    public void unbind() {
        CallingCodeDialogFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.listView = null;
        ((TextView) this.view2131755525).removeTextChangedListener(this.view2131755525TextWatcher);
        this.view2131755525TextWatcher = null;
        this.view2131755525 = null;
    }
}

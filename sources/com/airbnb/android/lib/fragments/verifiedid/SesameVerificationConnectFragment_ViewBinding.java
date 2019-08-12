package com.airbnb.android.lib.fragments.verifiedid;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirButton;

public class SesameVerificationConnectFragment_ViewBinding implements Unbinder {
    private SesameVerificationConnectFragment target;
    private View view2131756756;
    private TextWatcher view2131756756TextWatcher;
    private View view2131756757;
    private TextWatcher view2131756757TextWatcher;
    private View view2131756758;

    public SesameVerificationConnectFragment_ViewBinding(final SesameVerificationConnectFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.sesame_verification_submit, "field 'submitButton' and method 'submit'");
        target2.submitButton = (AirButton) Utils.castView(view, C0880R.C0882id.sesame_verification_submit, "field 'submitButton'", AirButton.class);
        this.view2131756758 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.submit();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.sesame_verification_full_name, "field 'fullName' and method 'onAfterTextChange'");
        target2.fullName = (EditText) Utils.castView(view2, C0880R.C0882id.sesame_verification_full_name, "field 'fullName'", EditText.class);
        this.view2131756756 = view2;
        this.view2131756756TextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
                target2.onAfterTextChange();
            }
        };
        ((TextView) view2).addTextChangedListener(this.view2131756756TextWatcher);
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.sesame_verification_gov_id, "field 'govID', method 'onEditorAction', and method 'onAfterTextChange'");
        target2.govID = (EditText) Utils.castView(view3, C0880R.C0882id.sesame_verification_gov_id, "field 'govID'", EditText.class);
        this.view2131756757 = view3;
        ((TextView) view3).setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView p0, int p1, KeyEvent p2) {
                return target2.onEditorAction();
            }
        });
        this.view2131756757TextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
                target2.onAfterTextChange();
            }
        };
        ((TextView) view3).addTextChangedListener(this.view2131756757TextWatcher);
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C0880R.C0882id.sesame_verification_loader, "field 'loaderFrame'", LoaderFrame.class);
    }

    public void unbind() {
        SesameVerificationConnectFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.submitButton = null;
        target2.fullName = null;
        target2.govID = null;
        target2.loaderFrame = null;
        this.view2131756758.setOnClickListener(null);
        this.view2131756758 = null;
        ((TextView) this.view2131756756).removeTextChangedListener(this.view2131756756TextWatcher);
        this.view2131756756TextWatcher = null;
        this.view2131756756 = null;
        ((TextView) this.view2131756757).setOnEditorActionListener(null);
        ((TextView) this.view2131756757).removeTextChangedListener(this.view2131756757TextWatcher);
        this.view2131756757TextWatcher = null;
        this.view2131756757 = null;
    }
}

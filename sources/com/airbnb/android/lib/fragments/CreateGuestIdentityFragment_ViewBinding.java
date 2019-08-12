package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.EditableCell;
import com.airbnb.android.lib.views.GroupedCell;

public class CreateGuestIdentityFragment_ViewBinding implements Unbinder {
    private CreateGuestIdentityFragment target;
    private View view2131756244;
    private View view2131756245;
    private View view2131756246;
    private View view2131756247;
    private View view2131756248;
    private View view2131756249;

    public CreateGuestIdentityFragment_ViewBinding(final CreateGuestIdentityFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.surname_cell, "field 'surnameCell' and method 'onEditableCellClick'");
        target2.surnameCell = (EditableCell) Utils.castView(view, C0880R.C0882id.surname_cell, "field 'surnameCell'", EditableCell.class);
        this.view2131756244 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onEditableCellClick((EditableCell) Utils.castParam(p0, "doClick", 0, "onEditableCellClick", 0));
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.given_name_cell, "field 'givenNameCell' and method 'onEditableCellClick'");
        target2.givenNameCell = (EditableCell) Utils.castView(view2, C0880R.C0882id.given_name_cell, "field 'givenNameCell'", EditableCell.class);
        this.view2131756245 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onEditableCellClick((EditableCell) Utils.castParam(p0, "doClick", 0, "onEditableCellClick", 0));
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.nationality_cell, "field 'nationalityCell' and method 'onNationalityClick'");
        target2.nationalityCell = (GroupedCell) Utils.castView(view3, C0880R.C0882id.nationality_cell, "field 'nationalityCell'", GroupedCell.class);
        this.view2131756246 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNationalityClick();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.identity_type_cell, "field 'idTypeCell' and method 'onIdentityTypeClick'");
        target2.idTypeCell = (GroupedCell) Utils.castView(view4, C0880R.C0882id.identity_type_cell, "field 'idTypeCell'", GroupedCell.class);
        this.view2131756247 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onIdentityTypeClick();
            }
        });
        View view5 = Utils.findRequiredView(source, C0880R.C0882id.identity_number_cell, "field 'idNumberCell' and method 'onEditableCellClick'");
        target2.idNumberCell = (EditableCell) Utils.castView(view5, C0880R.C0882id.identity_number_cell, "field 'idNumberCell'", EditableCell.class);
        this.view2131756248 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onEditableCellClick((EditableCell) Utils.castParam(p0, "doClick", 0, "onEditableCellClick", 0));
            }
        });
        View view6 = Utils.findRequiredView(source, C0880R.C0882id.date_of_expiry_cell, "field 'dateOfExpiryCell' and method 'onDateOfExpiryClick'");
        target2.dateOfExpiryCell = (GroupedCell) Utils.castView(view6, C0880R.C0882id.date_of_expiry_cell, "field 'dateOfExpiryCell'", GroupedCell.class);
        this.view2131756249 = view6;
        view6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onDateOfExpiryClick();
            }
        });
        target2.disclaimerTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.disclaimer, "field 'disclaimerTextView'", TextView.class);
    }

    public void unbind() {
        CreateGuestIdentityFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.surnameCell = null;
        target2.givenNameCell = null;
        target2.nationalityCell = null;
        target2.idTypeCell = null;
        target2.idNumberCell = null;
        target2.dateOfExpiryCell = null;
        target2.disclaimerTextView = null;
        this.view2131756244.setOnClickListener(null);
        this.view2131756244 = null;
        this.view2131756245.setOnClickListener(null);
        this.view2131756245 = null;
        this.view2131756246.setOnClickListener(null);
        this.view2131756246 = null;
        this.view2131756247.setOnClickListener(null);
        this.view2131756247 = null;
        this.view2131756248.setOnClickListener(null);
        this.view2131756248 = null;
        this.view2131756249.setOnClickListener(null);
        this.view2131756249 = null;
    }
}

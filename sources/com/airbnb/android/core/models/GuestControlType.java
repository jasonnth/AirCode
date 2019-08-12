package com.airbnb.android.core.models;

import com.airbnb.android.core.C0716R;

public enum GuestControlType {
    Children(C0716R.string.ml_house_rule_children, C0716R.string.children, C0716R.string.ml_house_rules_no_children),
    Infants(C0716R.string.ml_house_rule_infants, C0716R.string.infants, C0716R.string.ml_house_rules_no_infants),
    Pets(C0716R.string.ml_house_rule_pets, C0716R.string.pets, C0716R.string.ml_house_rules_no_pets),
    Smoking(C0716R.string.ml_house_rule_smoking, C0716R.string.smoking, C0716R.string.ml_house_rules_no_smoking),
    Events(C0716R.string.ml_house_rule_parties, C0716R.string.events, C0716R.string.ml_house_rules_no_parties);
    
    private final int disallowId;
    private final int labelId;
    private final int localizedName;

    private GuestControlType(int labelId2, int localizedName2, int disallowId2) {
        this.labelId = labelId2;
        this.localizedName = localizedName2;
        this.disallowId = disallowId2;
    }

    public int getLabelId() {
        return this.labelId;
    }

    public int getLocalizedName() {
        return this.localizedName;
    }

    public int getDisallowId() {
        return this.disallowId;
    }
}

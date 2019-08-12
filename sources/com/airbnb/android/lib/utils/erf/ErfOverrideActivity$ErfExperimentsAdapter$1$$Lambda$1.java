package com.airbnb.android.lib.utils.erf;

import com.airbnb.erf.Experiment;
import com.google.common.base.Predicate;

final /* synthetic */ class ErfOverrideActivity$ErfExperimentsAdapter$1$$Lambda$1 implements Predicate {
    private final CharSequence arg$1;

    private ErfOverrideActivity$ErfExperimentsAdapter$1$$Lambda$1(CharSequence charSequence) {
        this.arg$1 = charSequence;
    }

    public static Predicate lambdaFactory$(CharSequence charSequence) {
        return new ErfOverrideActivity$ErfExperimentsAdapter$1$$Lambda$1(charSequence);
    }

    public boolean apply(Object obj) {
        return ((Experiment) obj).getName().toLowerCase().contains(this.arg$1.toString().toLowerCase());
    }
}

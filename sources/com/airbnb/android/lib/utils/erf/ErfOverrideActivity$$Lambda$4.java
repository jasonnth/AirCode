package com.airbnb.android.lib.utils.erf;

import com.airbnb.erf.Experiment;
import java.util.Comparator;

final /* synthetic */ class ErfOverrideActivity$$Lambda$4 implements Comparator {
    private static final ErfOverrideActivity$$Lambda$4 instance = new ErfOverrideActivity$$Lambda$4();

    private ErfOverrideActivity$$Lambda$4() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((Experiment) obj).getName().compareToIgnoreCase(((Experiment) obj2).getName());
    }
}

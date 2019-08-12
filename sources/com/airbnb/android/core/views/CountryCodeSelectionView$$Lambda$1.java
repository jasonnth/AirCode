package com.airbnb.android.core.views;

import com.airbnb.android.core.presenters.CountryCodeItem;
import java.text.Collator;
import java.util.Comparator;

final /* synthetic */ class CountryCodeSelectionView$$Lambda$1 implements Comparator {
    private final Collator arg$1;

    private CountryCodeSelectionView$$Lambda$1(Collator collator) {
        this.arg$1 = collator;
    }

    public static Comparator lambdaFactory$(Collator collator) {
        return new CountryCodeSelectionView$$Lambda$1(collator);
    }

    public int compare(Object obj, Object obj2) {
        return this.arg$1.compare(((CountryCodeItem) obj).getDisplayCountryName(), ((CountryCodeItem) obj2).getDisplayCountryName());
    }
}

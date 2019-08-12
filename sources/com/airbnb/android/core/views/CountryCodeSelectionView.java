package com.airbnb.android.core.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.p027n2.collections.BaseSelectionView;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CountryCodeSelectionView extends BaseSelectionView<CountryCodeItem> {
    public CountryCodeSelectionView(Context context) {
        super(context);
        init();
    }

    public CountryCodeSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CountryCodeSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setItems((List<T>) createCountryCodeItems(getContext(), ""));
    }

    private static ArrayList<CountryCodeItem> createCountryCodeItems(Context context, String searchTerm) {
        Resources resources = context.getResources();
        Locale currentLocale = resources.getConfiguration().locale;
        String lowerCaseSearchTerm = searchTerm.toLowerCase();
        ArrayList<CountryCodeItem> callingCodeEntries = new ArrayList<>();
        for (String mapping : resources.getStringArray(C0716R.array.country_codes)) {
            String[] entryValues = mapping.split(",");
            String callingCode = entryValues[0];
            String countryCode = entryValues[1];
            String countryDisplayName = new Locale("", countryCode).getDisplayName(currentLocale);
            if (countryDisplayName.toLowerCase().contains(lowerCaseSearchTerm)) {
                callingCodeEntries.add(new CountryCodeItem(callingCode, countryCode, countryDisplayName));
            }
        }
        Collator collator = Collator.getInstance(currentLocale);
        collator.setStrength(0);
        Collections.sort(callingCodeEntries, CountryCodeSelectionView$$Lambda$1.lambdaFactory$(collator));
        return callingCodeEntries;
    }

    public void setSelectedCountryCode(String countryCode) {
        List<CountryCodeItem> countryCodeItems = getItems();
        for (CountryCodeItem countryCodeItem : countryCodeItems) {
            if (countryCodeItem.getCountryCode().equals(countryCode)) {
                setSelectedItem(countryCodeItem);
                scrollToPosition(countryCodeItems.indexOf(countryCodeItem));
                return;
            }
        }
    }

    public void setItemsFromSearchTerm(String searchTerm) {
        setItems((List<T>) createCountryCodeItems(getContext(), searchTerm));
    }

    public String getSelectedCountryCode() {
        CountryCodeItem selectionSheetItem = (CountryCodeItem) getSelectedItem();
        if (selectionSheetItem == null) {
            return null;
        }
        return selectionSheetItem.getCountryCode();
    }
}

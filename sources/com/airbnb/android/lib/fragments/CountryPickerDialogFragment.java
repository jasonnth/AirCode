package com.airbnb.android.lib.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.models.NameCodeItem;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.NameCodeListAdapter;
import com.airbnb.android.utils.SimpleTextWatcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CountryPickerDialogFragment extends AirDialogFragment {
    private static final String ARG_COUNTRY_CODES = "country_codes";
    private static final String ARG_COUNTRY_NAMES = "country_names";
    private static final String ARG_SELECTED_COUNTRY = "sel_country_code";
    private static final String ARG_TITLE = "dialog_title";
    public static final String EXTRA_COUNTRY = "country";
    private static final int USER_COUNTRY_INSERT_INDEX = 0;
    /* access modifiers changed from: private */
    public boolean addedUserCountryAtBeginning;
    /* access modifiers changed from: private */
    public List<NameCodeItem> mCountriesList;
    private List<NameCodeItem> mCountriesListSearchSubset;
    private NameCodeListAdapter mListAdapter;

    public static CountryPickerDialogFragment newInstance(String dialogTitle, String countryCode) {
        return newInstance(dialogTitle, countryCode, null, null);
    }

    public static CountryPickerDialogFragment newInstance(String dialogTitle, String countryCode, ArrayList<String> countries) {
        return newInstance(dialogTitle, countryCode, countries, null);
    }

    public static CountryPickerDialogFragment newInstance(String dialogTitle, String countryCode, ArrayList<String> countryCodes, ArrayList<String> countryNames) {
        CountryPickerDialogFragment picker = new CountryPickerDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, dialogTitle);
        bundle.putString(ARG_SELECTED_COUNTRY, countryCode);
        bundle.putStringArrayList(ARG_COUNTRY_CODES, countryCodes);
        bundle.putStringArrayList(ARG_COUNTRY_NAMES, countryNames);
        picker.setArguments(bundle);
        return picker;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_country_picker, null);
        Bundle args = getArguments();
        if (args != null) {
            getDialog().setTitle(args.getString(ARG_TITLE));
        }
        buildCountryList();
        ListView countryListView = (ListView) view.findViewById(C0880R.C0882id.country_picker_listview);
        this.mListAdapter = new NameCodeListAdapter(getActivity(), this.mCountriesListSearchSubset);
        countryListView.setAdapter(this.mListAdapter);
        countryListView.setOnItemClickListener(CountryPickerDialogFragment$$Lambda$1.lambdaFactory$(this));
        ((EditText) view.findViewById(C0880R.C0882id.country_picker_search)).addTextChangedListener(new SimpleTextWatcher() {
            public void afterTextChanged(Editable s) {
                if (CountryPickerDialogFragment.this.addedUserCountryAtBeginning) {
                    CountryPickerDialogFragment.this.addedUserCountryAtBeginning = false;
                    CountryPickerDialogFragment.this.mCountriesList.remove(0);
                }
                CountryPickerDialogFragment.this.search(s.toString());
            }
        });
        new Handler().postDelayed(CountryPickerDialogFragment$$Lambda$2.lambdaFactory$(getIndexOf(this.mCountriesList, args.getString(ARG_SELECTED_COUNTRY)), countryListView), 50);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(CountryPickerDialogFragment countryPickerDialogFragment, AdapterView parent, View v, int position, long id) {
        NameCodeItem nameCodeItem = (NameCodeItem) countryPickerDialogFragment.mCountriesListSearchSubset.get(position);
        Fragment f = countryPickerDialogFragment.getTargetFragment();
        if (f != null) {
            countryPickerDialogFragment.dismiss();
            Intent intent = new Intent();
            intent.putExtra("country", nameCodeItem);
            f.onActivityResult(countryPickerDialogFragment.getTargetRequestCode(), -1, intent);
        }
    }

    static /* synthetic */ void lambda$onCreateView$1(int selectedItem, ListView countryListView) {
        if (selectedItem != -1) {
            countryListView.setSelection(selectedItem);
        }
    }

    private void buildCountryList() {
        ArrayList<String> countryCodeList = getArguments().getStringArrayList(ARG_COUNTRY_CODES);
        if (countryCodeList == null) {
            countryCodeList = new ArrayList<>(Arrays.asList(getResources().getStringArray(C0880R.array.approved_countries)));
        } else {
            countryCodeList.removeAll(Collections.singleton(null));
        }
        ArrayList<String> countryNameList = getArguments().getStringArrayList(ARG_COUNTRY_NAMES);
        boolean sort = countryNameList == null;
        if (countryNameList == null) {
            countryNameList = new ArrayList<>();
            String language = Locale.getDefault().getLanguage();
            Iterator it = countryCodeList.iterator();
            while (it.hasNext()) {
                countryNameList.add(new Locale(language, (String) it.next()).getDisplayCountry());
            }
        }
        User currentUser = AirbnbApplication.instance(getActivity()).component().accountManager().getCurrentUser();
        String userCountry = currentUser == null ? null : currentUser.getCountryOfResidence();
        if (TextUtils.isEmpty(userCountry)) {
            userCountry = Locale.getDefault().getCountry();
        }
        NameCodeItem userCountryNameCodeItem = null;
        this.mCountriesList = new ArrayList();
        for (int i = 0; i < countryCodeList.size(); i++) {
            NameCodeItem c = new NameCodeItem();
            String countryCode = (String) countryCodeList.get(i);
            c.setCode(countryCode);
            c.setName((String) countryNameList.get(i));
            this.mCountriesList.add(c);
            if (countryCode.equals(userCountry)) {
                userCountryNameCodeItem = c;
            }
        }
        if (sort) {
            Collections.sort(this.mCountriesList, CountryPickerDialogFragment$$Lambda$3.lambdaFactory$());
        }
        if (userCountryNameCodeItem != null) {
            this.addedUserCountryAtBeginning = true;
            this.mCountriesList.add(0, userCountryNameCodeItem);
        }
        this.mCountriesListSearchSubset = new ArrayList(this.mCountriesList);
    }

    private static int getIndexOf(List<NameCodeItem> countriesList, String countryCode) {
        for (int i = 0; i < countriesList.size(); i++) {
            if (((NameCodeItem) countriesList.get(i)).getCode().equals(countryCode)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    @SuppressLint({"DefaultLocale"})
    public void search(String text) {
        this.mCountriesListSearchSubset.clear();
        if (TextUtils.isEmpty(text)) {
            this.mCountriesListSearchSubset.addAll(this.mCountriesList);
        } else {
            for (NameCodeItem nameCodeItem : this.mCountriesList) {
                if (nameCodeItem.getName().toLowerCase().contains(text.toLowerCase())) {
                    this.mCountriesListSearchSubset.add(nameCodeItem);
                }
            }
        }
        this.mListAdapter.notifyDataSetChanged();
    }
}

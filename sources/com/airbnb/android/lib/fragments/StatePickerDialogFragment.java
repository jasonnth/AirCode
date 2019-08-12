package com.airbnb.android.lib.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.interfaces.NameCodePickerListener;
import com.airbnb.android.core.models.NameCodeItem;
import com.airbnb.android.core.utils.LocationUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.NameCodeListAdapter;
import java.util.ArrayList;
import java.util.List;

public class StatePickerDialogFragment extends AirDialogFragment {
    private static final String ARG_STATE_CODE = "stateCode";
    private static final String ARG_TITLE = "dialogTitle";
    public static final String EXTRA_STATE = "state";
    private NameCodeListAdapter adapter;
    private List<NameCodeItem> allStatesList;
    private ListView countryListView;
    private NameCodePickerListener listener;
    private int mSelectedItem;
    private List<NameCodeItem> selectedStatesList;

    public void setListener(NameCodePickerListener listener2) {
        this.listener = listener2;
    }

    public static StatePickerDialogFragment newInstance(String dialogTitle, String stateCode) {
        StatePickerDialogFragment picker = new StatePickerDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE, dialogTitle);
        bundle.putString(ARG_STATE_CODE, stateCode);
        picker.setArguments(bundle);
        return picker;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.allStatesList = LocationUtil.getAllStates(getContext());
        this.selectedStatesList = new ArrayList(this.allStatesList);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_country_picker, null);
        this.mSelectedItem = -1;
        String stateCode = null;
        Bundle args = getArguments();
        if (args != null) {
            getDialog().setTitle(args.getString(ARG_TITLE));
            stateCode = args.getString(ARG_STATE_CODE);
        }
        EditText searchEditText = (EditText) view.findViewById(C0880R.C0882id.country_picker_search);
        this.countryListView = (ListView) view.findViewById(C0880R.C0882id.country_picker_listview);
        this.adapter = new NameCodeListAdapter(getActivity(), this.selectedStatesList);
        this.countryListView.setAdapter(this.adapter);
        this.countryListView.setOnItemClickListener(StatePickerDialogFragment$$Lambda$1.lambdaFactory$(this));
        searchEditText.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                StatePickerDialogFragment.this.search(s.toString());
            }
        });
        if (!TextUtils.isEmpty(stateCode)) {
            int i = 0;
            while (true) {
                if (i >= this.allStatesList.size()) {
                    break;
                } else if (((NameCodeItem) this.allStatesList.get(i)).getCode().equals(stateCode)) {
                    this.mSelectedItem = i;
                    break;
                } else {
                    i++;
                }
            }
        }
        new Handler().postDelayed(StatePickerDialogFragment$$Lambda$2.lambdaFactory$(this), 50);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(StatePickerDialogFragment statePickerDialogFragment, AdapterView parent, View view1, int position, long id) {
        NameCodeItem nameCodeItem = (NameCodeItem) statePickerDialogFragment.selectedStatesList.get(position);
        if (statePickerDialogFragment.listener != null) {
            statePickerDialogFragment.listener.onSelectItem(nameCodeItem.getName(), nameCodeItem.getCode());
        }
        Fragment f = statePickerDialogFragment.getTargetFragment();
        if (f != null) {
            statePickerDialogFragment.dismiss();
            Intent intent = new Intent();
            intent.putExtra("state", nameCodeItem);
            f.onActivityResult(statePickerDialogFragment.getTargetRequestCode(), -1, intent);
        }
    }

    static /* synthetic */ void lambda$onCreateView$1(StatePickerDialogFragment statePickerDialogFragment) {
        if (statePickerDialogFragment.mSelectedItem >= 0) {
            statePickerDialogFragment.countryListView.setSelection(statePickerDialogFragment.mSelectedItem);
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"DefaultLocale"})
    public void search(String text) {
        this.selectedStatesList.clear();
        if (TextUtils.isEmpty(text)) {
            this.selectedStatesList.addAll(this.allStatesList);
        } else {
            for (NameCodeItem nameCodeItem : this.allStatesList) {
                if (nameCodeItem.getName().toLowerCase().contains(text.toLowerCase())) {
                    this.selectedStatesList.add(nameCodeItem);
                }
            }
        }
        this.adapter.notifyDataSetChanged();
    }
}

package com.airbnb.android.core.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.interfaces.CallingCodeSelectedListener;
import com.airbnb.android.core.models.CallingCodeEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class CallingCodeDialogFragment extends MatchParentWidthDialogFragment {
    public static final String TAG = CallingCodeDialogFragment.class.getSimpleName();
    private CallingCodeAdapter adapter;
    private final OnItemClickListener itemClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            CallingCodeDialogFragment.this.listener.onSelectedCallingCode((CallingCodeEntry) parent.getAdapter().getItem(position));
            CallingCodeDialogFragment.this.dismiss();
        }
    };
    @BindView
    ListView listView;
    /* access modifiers changed from: private */
    public CallingCodeSelectedListener listener;

    private static class CallingCodeAdapter extends ArrayAdapter<CallingCodeEntry> {
        private ArrayList<CallingCodeEntry> callingCodeEntries;

        public CallingCodeAdapter(Context context) {
            super(context, C0716R.layout.list_item_calling_code);
            this.callingCodeEntries = CallingCodeDialogFragment.setUpEntries(context, "");
        }

        public int getCount() {
            return this.callingCodeEntries.size();
        }

        public CallingCodeEntry getItem(int position) {
            return (CallingCodeEntry) this.callingCodeEntries.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(C0716R.layout.list_item_calling_code, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.callingCodeTextView = (TextView) ButterKnife.findById(convertView, C0716R.C0718id.text_calling_code);
                viewHolder.countryNameTextView = (TextView) ButterKnife.findById(convertView, C0716R.C0718id.text_country_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            CallingCodeEntry callingCodeEntry = getItem(position);
            String callingCodeText = "+" + callingCodeEntry.getCallingCode();
            String countryNameText = callingCodeEntry.getDisplayCountryName();
            viewHolder.callingCodeTextView.setText(callingCodeText);
            viewHolder.countryNameTextView.setText(countryNameText);
            return convertView;
        }

        public void updateWithSearchTerm(String searchTerm) {
            this.callingCodeEntries = CallingCodeDialogFragment.setUpEntries(getContext(), searchTerm);
            notifyDataSetChanged();
        }
    }

    private static class ViewHolder {
        TextView callingCodeTextView;
        TextView countryNameTextView;

        private ViewHolder() {
        }
    }

    public static CallingCodeDialogFragment newInstance(CallingCodeSelectedListener listener2) {
        CallingCodeDialogFragment fragment = new CallingCodeDialogFragment();
        fragment.listener = listener2;
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_search_calling_code, null);
        ButterKnife.bind(this, view);
        setUpWindow();
        this.adapter = new CallingCodeAdapter(getContext());
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener(this.itemClickListener);
        return view;
    }

    private void setUpWindow() {
        Window window = getDialog().getWindow();
        window.requestFeature(1);
        window.setGravity(48);
    }

    /* access modifiers changed from: 0000 */
    @OnTextChanged
    public void updateSearch(CharSequence searchTerm) {
        this.adapter.updateWithSearchTerm(searchTerm.toString());
    }

    public void setListener(CallingCodeSelectedListener listener2) {
        this.listener = listener2;
    }

    /* access modifiers changed from: private */
    public static ArrayList<CallingCodeEntry> setUpEntries(Context context, String searchTerm) {
        Resources resources = context.getResources();
        Locale currentLocale = resources.getConfiguration().locale;
        ArrayList<CallingCodeEntry> callingCodeEntries = new ArrayList<>();
        for (String mapping : resources.getStringArray(C0716R.array.country_codes)) {
            String[] entryValues = mapping.split(",");
            String callingCode = entryValues[0];
            String countryCode = entryValues[1];
            String countryDisplayName = new Locale("", countryCode).getDisplayName(currentLocale);
            if (countryDisplayName.toLowerCase().contains(searchTerm.toLowerCase())) {
                callingCodeEntries.add(new CallingCodeEntry(callingCode, countryCode, countryDisplayName));
            }
        }
        Collections.sort(callingCodeEntries);
        return callingCodeEntries;
    }
}

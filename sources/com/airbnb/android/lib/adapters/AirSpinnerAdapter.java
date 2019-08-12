package com.airbnb.android.lib.adapters;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public class AirSpinnerAdapter implements SpinnerAdapter {
    private final String[] mDropdownTitles;
    private final String[] mSpinnerSubtitles;
    private final String[] mSpinnerTitles;

    public AirSpinnerAdapter(String[] spinnerTitles, String[] spinnerSubtitles, String[] dropdownTitles) {
        this.mSpinnerTitles = spinnerTitles;
        this.mSpinnerSubtitles = spinnerSubtitles;
        this.mDropdownTitles = dropdownTitles;
        if (this.mSpinnerTitles.length != this.mDropdownTitles.length) {
            throw new IllegalArgumentException("spinner and dropdown sizes dont match");
        }
    }

    public int getCount() {
        return this.mSpinnerTitles.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public int getItemViewType(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        boolean isLandscape = false;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.reviews_spinner_view, parent, false);
        }
        ((TextView) ButterKnife.findById(convertView, C0880R.C0882id.reviews_spinner_title)).setText(this.mSpinnerTitles[position]);
        if (this.mSpinnerSubtitles != null) {
            TextView spinnerSubtitleText = (TextView) ButterKnife.findById(convertView, C0880R.C0882id.reviews_spinner_subtitle);
            spinnerSubtitleText.setVisibility(0);
            spinnerSubtitleText.setText(this.mSpinnerSubtitles[position]);
            if (convertView.getResources().getConfiguration().orientation == 2) {
                isLandscape = true;
            }
            ViewUtils.setGoneIf(ButterKnife.findById(convertView, C0880R.C0882id.reviews_spinner_title), isLandscape);
        }
        ((TextView) ButterKnife.findById(convertView, C0880R.C0882id.dropdown_text)).setText(this.mDropdownTitles[position]);
        return convertView;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public void registerDataSetObserver(DataSetObserver observer) {
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.sherlock_spinner_dropdown_item, parent, false);
        }
        ((TextView) convertView.findViewById(C0880R.C0882id.dropdown_text)).setText(this.mDropdownTitles[position]);
        return convertView;
    }
}

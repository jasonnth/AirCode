package com.android.p305ex.chips;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.android.p305ex.chips.DropdownChipLayouter.AdapterType;

/* renamed from: com.android.ex.chips.SingleRecipientArrayAdapter */
class SingleRecipientArrayAdapter extends ArrayAdapter<RecipientEntry> {
    private final DropdownChipLayouter mDropdownChipLayouter;

    public SingleRecipientArrayAdapter(Context context, RecipientEntry entry, DropdownChipLayouter dropdownChipLayouter) {
        super(context, dropdownChipLayouter.getAlternateItemLayoutResId(), new RecipientEntry[]{entry});
        this.mDropdownChipLayouter = dropdownChipLayouter;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return this.mDropdownChipLayouter.bindView(convertView, parent, (RecipientEntry) getItem(position), position, AdapterType.SINGLE_RECIPIENT, null);
    }
}

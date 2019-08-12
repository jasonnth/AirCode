package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.airbnb.android.core.models.SecurityCheckPhoneNumber;
import com.airbnb.android.lib.C0880R;
import java.util.List;

public class PhoneNumberPickerAdapter extends ArrayAdapter<SecurityCheckPhoneNumber> {
    public PhoneNumberPickerAdapter(Context context, int resource, List<SecurityCheckPhoneNumber> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(17367043, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(16908308);
        textView.setGravity(17);
        textView.setText(parent.getContext().getString(C0880R.string.phone_number_ending_in, new Object[]{((SecurityCheckPhoneNumber) getItem(position)).getLastFourDigits()}));
        return convertView;
    }
}

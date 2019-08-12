package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.airbnb.android.core.models.Currency;
import com.airbnb.android.lib.C0880R;
import java.util.List;

public class CurrencyAdapter extends ArrayAdapter<Currency> {
    private final int mSelected;

    class ViewHolder {
        TextView name;
        RadioButton radioButton;
        TextView symbol;

        ViewHolder() {
        }
    }

    public CurrencyAdapter(Context context, int resource, int textViewResourceId, List<Currency> currencies, int selected) {
        super(context, resource, textViewResourceId, currencies);
        this.mSelected = selected;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C0880R.layout.list_item_currency, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(C0880R.C0882id.currency_name);
            holder.symbol = (TextView) convertView.findViewById(C0880R.C0882id.currency_symbol);
            holder.radioButton = (RadioButton) convertView.findViewById(C0880R.C0882id.radio_button);
            convertView.setTag(holder);
        }
        ViewHolder holder2 = (ViewHolder) convertView.getTag();
        Currency currency = (Currency) getItem(position);
        holder2.name.setText(currency.getCode());
        holder2.symbol.setText("‎" + currency.getUnicodeSymbol());
        holder2.radioButton.setChecked(position == this.mSelected);
        convertView.setOnTouchListener(CurrencyAdapter$$Lambda$1.lambdaFactory$(holder2));
        return convertView;
    }
}

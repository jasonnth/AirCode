package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.airbnb.android.core.models.NameCodeItem;
import java.util.List;

public class NameCodeListAdapter extends BaseAdapter {
    final LayoutInflater inflater;
    final List<NameCodeItem> nameCodeItems;

    static class Cell {
        public TextView textView;

        Cell() {
        }
    }

    public NameCodeListAdapter(Context context, List<NameCodeItem> nameCodeItems2) {
        this.nameCodeItems = nameCodeItems2;
        this.inflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.nameCodeItems.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Cell cell;
        View cellView = convertView;
        NameCodeItem nameCodeItem = (NameCodeItem) this.nameCodeItems.get(position);
        if (convertView == null) {
            cell = new Cell();
            cellView = this.inflater.inflate(17367043, null);
            cell.textView = (TextView) cellView.findViewById(16908308);
            cellView.setTag(cell);
        } else {
            cell = (Cell) cellView.getTag();
        }
        cell.textView.setText(nameCodeItem.getName());
        return cellView;
    }
}

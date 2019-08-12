package com.jumio.commons.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.jumio.commons.utils.ScreenUtil;
import java.util.ArrayList;

public class SpinnerAdapterWithHintAndSelectionReset extends ArrayAdapter<String> {
    protected int EXTRA;
    protected Context context;
    private boolean isSelectionResettable;
    protected LayoutInflater layoutInflater;
    private int promptColor;
    private String promptText;
    private String resetSelectionText;
    private int textColor;

    public SpinnerAdapterWithHintAndSelectionReset(Context context2, ArrayList<String> items, String promptText2, boolean isSelectionResettable2, String resetSelectionText2, int textColor2, int promptColor2) {
        int i = 1;
        super(context2, 17367048, items);
        this.EXTRA = 1;
        this.context = context2;
        this.layoutInflater = LayoutInflater.from(context2);
        this.promptText = promptText2;
        this.resetSelectionText = resetSelectionText2;
        this.isSelectionResettable = isSelectionResettable2;
        this.textColor = textColor2;
        this.promptColor = promptColor2;
        if (!isSelectionResettable2) {
            i = 0;
        }
        this.EXTRA = i;
    }

    public SpinnerAdapterWithHintAndSelectionReset(Context context2, ArrayList<String> items, String promptText2, boolean isSelectionResettable2) {
        this(context2, items, promptText2, isSelectionResettable2, "", 0, 0);
    }

    public final View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            int dp1 = ScreenUtil.dpToPx(this.context, 1);
            int dp23 = ScreenUtil.dpToPx(this.context, 23);
            convertView = (TextView) this.layoutInflater.inflate(17367048, parent, false);
            ((TextView) convertView).setGravity(5);
            ((TextView) convertView).setTextSize(2, 20.0f);
            ((TextView) convertView).setPadding(dp1, dp1, dp23, dp1);
        }
        if (position != 0 || !this.isSelectionResettable) {
            ((TextView) convertView).setText(getItem(position));
            ((TextView) convertView).setTextColor(this.textColor);
        } else {
            ((TextView) convertView).setText(this.promptText);
            ((TextView) convertView).setTextColor(this.promptColor);
        }
        return convertView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !(convertView instanceof TextView)) {
            convertView = this.layoutInflater.inflate(17367049, parent, false);
            ((TextView) convertView).setTextSize(2, 18.0f);
            ((TextView) convertView).setTextColor(this.textColor);
        }
        if (position != 0 || !this.isSelectionResettable) {
            ((TextView) convertView).setText(getItem(position));
        } else {
            ((TextView) convertView).setText(this.resetSelectionText);
        }
        return convertView;
    }

    public int getCount() {
        return super.getCount() + this.EXTRA;
    }

    public String getItem(int position) {
        if (position != 0 || !this.isSelectionResettable) {
            return (String) super.getItem(position - this.EXTRA);
        }
        return null;
    }

    public long getItemId(int position) {
        if (position != 0 || !this.isSelectionResettable) {
            return super.getItemId(position - this.EXTRA);
        }
        return 0;
    }
}

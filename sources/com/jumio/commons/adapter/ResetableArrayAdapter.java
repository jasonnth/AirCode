package com.jumio.commons.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ResetableArrayAdapter extends ArrayAdapter<String> {
    protected int EXTRA = 1;
    protected Context context;
    private int gravity = 3;
    protected LayoutInflater layoutInflater;
    private int promptColor;
    private String resetText;
    private boolean resetable;
    private int textColor;

    public ResetableArrayAdapter(Context context2, ArrayList<String> items) {
        super(context2, 17367048, items);
        this.context = context2;
        this.layoutInflater = LayoutInflater.from(context2);
    }

    public final View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.layoutInflater.inflate(17367049, parent, false);
            ((TextView) convertView).setTextSize(2, 18.0f);
            ((TextView) convertView).setTextColor(this.textColor);
            ((TextView) convertView).setGravity(this.gravity);
        }
        if (position != 0 || !this.resetable) {
            ((TextView) convertView).setText(getItem(position));
            ((TextView) convertView).setTextColor(this.textColor);
        } else {
            ((TextView) convertView).setText(this.resetText);
            ((TextView) convertView).setTextColor(this.promptColor);
        }
        return convertView;
    }

    public int getCount() {
        return super.getCount() + this.EXTRA;
    }

    public String getItem(int position) {
        return (position != 0 || !this.resetable) ? (String) super.getItem(position - this.EXTRA) : this.resetText;
    }

    public long getItemId(int position) {
        if (position != 0 || !this.resetable) {
            return super.getItemId(position - this.EXTRA);
        }
        return 0;
    }

    public void setGravity(int gravity2) {
        this.gravity = gravity2;
    }

    public void setResetable(boolean resetable2) {
        this.resetable = resetable2;
        this.EXTRA = resetable2 ? 1 : 0;
    }

    public void setTextColor(int textColor2) {
        this.textColor = textColor2;
    }

    public void setPromptColor(int promptColor2) {
        this.promptColor = promptColor2;
    }

    public void setResetText(String resetText2) {
        this.resetText = resetText2;
    }
}

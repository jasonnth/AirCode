package com.airbnb.android.lib.adapters.viewholders;

import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirTextView;

public class CheckedTextRowViewHolder extends ViewHolder {
    @BindView
    View check;
    @BindView
    AirTextView description;
    @BindView
    AirTextView title;

    public CheckedTextRowViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.checkmarked_text_row, parent, false));
        ButterKnife.bind(this, this.itemView);
    }

    public void bind(String titleStr, String descStr, boolean showCheckMark) {
        boolean isValid;
        boolean isValid2;
        boolean z = true;
        if (!TextUtils.isEmpty(titleStr)) {
            isValid = true;
        } else {
            isValid = false;
        }
        if (isValid) {
            this.title.setText(titleStr);
        }
        ViewUtils.setVisibleIf((View) this.title, isValid);
        if (!TextUtils.isEmpty(descStr)) {
            isValid2 = true;
        } else {
            isValid2 = false;
        }
        if (isValid2) {
            this.description.setText(descStr);
        }
        ViewUtils.setVisibleIf((View) this.description, isValid2);
        View view = this.check;
        if (showCheckMark) {
            z = false;
        }
        ViewUtils.setInvisibleIf(view, z);
    }
}

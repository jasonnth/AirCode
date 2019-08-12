package com.airbnb.p027n2.components.decide.select;

import android.content.Context;
import android.util.AttributeSet;
import butterknife.BindView;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.BaseDividerComponent;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.decide.select.SelectHomeSummaryRow */
public class SelectHomeSummaryRow extends BaseDividerComponent {
    @BindView
    AirTextView bathroomsTextView;
    @BindView
    AirTextView bedroomsTextView;
    @BindView
    AirTextView bedsTextView;
    @BindView
    AirTextView guestTextView;

    public SelectHomeSummaryRow(Context context) {
        super(context);
    }

    public SelectHomeSummaryRow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectHomeSummaryRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return R.layout.n2_select_home_summary;
    }

    public void setGuestsText(CharSequence text) {
        this.guestTextView.setText(text);
    }

    public void setBedroomsText(CharSequence text) {
        this.bedroomsTextView.setText(text);
    }

    public void setBedsText(CharSequence text) {
        this.bedsTextView.setText(text);
    }

    public void setBathroomsText(CharSequence text) {
        this.bathroomsTextView.setText(text);
    }

    public static void mock(SelectHomeSummaryRow selectHomeSummaryRow) {
        selectHomeSummaryRow.setGuestsText("Sleeps 2");
        selectHomeSummaryRow.setBedroomsText("2 bedrooms");
        selectHomeSummaryRow.setBedsText("2 beds");
        selectHomeSummaryRow.setBathroomsText("1.5 bath");
    }
}

package com.airbnb.android.places.views;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.models.PlaceActivityAttribute;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.AirmojiEnum;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

public class PlaceActivityDetailsView extends LinearLayout implements DividerView {
    @BindView
    View sectionDivider;

    public PlaceActivityDetailsView(Context context) {
        super(context);
        init();
    }

    public PlaceActivityDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlaceActivityDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C7627R.layout.view_place_activity_details, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    private LinearLayout createItemView(PlaceActivityAttribute item) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(getContext()).inflate(C7627R.layout.view_place_activity_detail_item, this, false);
        ((AirTextView) view.findViewById(C7627R.C7629id.airmoji)).setText(AirmojiEnum.fromKey(item.getAirmoji()));
        SpannableStringBuilder sb = new SpannableStringBuilder();
        CharSequence title = item.getTitle();
        if (!TextUtils.isEmpty(title)) {
            sb.append(SpannableUtils.makeBookString(title, getContext()));
            sb.append("\n");
        }
        sb.append(item.getText());
        ((AirTextView) view.findViewById(C7627R.C7629id.body)).setText(sb);
        return view;
    }

    public void setItems(List<PlaceActivityAttribute> items) {
        removeViews(0, getChildCount() - 1);
        for (int i = 0; i < items.size(); i++) {
            addView(createItemView((PlaceActivityAttribute) items.get(i)), i);
        }
    }

    public void showDivider(boolean show) {
        ViewLibUtils.setVisibleIf(this.sectionDivider, show);
    }
}

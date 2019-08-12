package com.airbnb.p027n2.components;

import android.animation.LayoutTransition;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.TextUtil;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.n2.components.ExpandableCollectionRow */
public class ExpandableCollectionRow extends LinearLayout implements DividerView {
    @BindView
    AirTextView bottomText;
    @BindView
    LinearLayout container;
    @BindView
    View divider;
    @BindView
    AirTextView expandText;

    /* renamed from: com.airbnb.n2.components.ExpandableCollectionRow$RowItem */
    public static class RowItem {
        public final CharSequence leftText;
        public final CharSequence rightText;

        public RowItem(CharSequence leftText2, CharSequence rightText2) {
            this.leftText = leftText2;
            this.rightText = rightText2;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (TextUtils.isEmpty(this.leftText) ? 0 : this.leftText.hashCode()) * 31;
            if (!TextUtils.isEmpty(this.rightText)) {
                i = this.rightText.hashCode();
            }
            return hashCode + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            RowItem rowItem = (RowItem) obj;
            if (this.leftText == null ? rowItem.leftText != null : !this.leftText.equals(rowItem)) {
                return false;
            }
            if (this.rightText != null) {
                if (this.rightText.equals(rowItem)) {
                    return true;
                }
            } else if (rowItem.rightText == null) {
                return true;
            }
            return false;
        }
    }

    public ExpandableCollectionRow(Context context) {
        super(context);
        init();
    }

    public ExpandableCollectionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExpandableCollectionRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_expandable_collection_row, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
        setLayoutTransition(new LayoutTransition());
        this.expandText.setOnClickListener(ExpandableCollectionRow$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$init$0(ExpandableCollectionRow expandableCollectionRow, View v) {
        expandableCollectionRow.expand();
        expandableCollectionRow.expandText.setVisibility(8);
    }

    public void setRowItems(List<RowItem> rowItems) {
        setRowItems(rowItems, true);
    }

    public void setRowItems(List<RowItem> rowItems, boolean boldFirstRow) {
        this.container.removeAllViews();
        int numVisibleRows = 2;
        if (rowItems.size() <= 2) {
            this.expandText.setVisibility(8);
        }
        for (RowItem rowItem : rowItems) {
            LinearLayout view = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.n2_expandable_collection_row_item, this, false);
            AirTextView leftTextView = (AirTextView) view.findViewById(R.id.left_text);
            AirTextView rightTextView = (AirTextView) view.findViewById(R.id.right_text);
            CharSequence leftText = rowItem.leftText;
            CharSequence rightText = rowItem.rightText;
            if (boldFirstRow) {
                leftText = TextUtil.makeCircularBold(getContext(), rowItem.leftText);
                rightText = TextUtil.makeCircularBold(getContext(), rowItem.rightText);
                boldFirstRow = false;
            }
            if (numVisibleRows == 0) {
                view.setVisibility(8);
            } else {
                numVisibleRows--;
            }
            leftTextView.setText(leftText);
            rightTextView.setText(rightText);
            this.container.addView(view);
        }
    }

    public void expand() {
        for (int i = 0; i < this.container.getChildCount(); i++) {
            this.container.getChildAt(i).setVisibility(0);
        }
    }

    public void setExpandText(CharSequence text) {
        this.expandText.setText(text);
        this.expandText.setVisibility(0);
    }

    public void setExpandText(int textRes) {
        setExpandText((CharSequence) getContext().getString(textRes));
    }

    public void setBottomText(CharSequence text) {
        this.bottomText.setText(text);
    }

    public void setBottomText(int textRes) {
        setBottomText((CharSequence) getContext().getString(textRes));
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mockMoreThan2Items(ExpandableCollectionRow view) {
        view.setBottomText((CharSequence) "disclaimer text. AKA: Can't blame us when things go wrong text long something words more words yep.");
        view.setExpandText((CharSequence) "Click to expand me");
        List<RowItem> items = new ArrayList<>();
        items.add(new RowItem("1st item", "put"));
        items.add(new RowItem("2nd item", "a"));
        items.add(new RowItem("3rd item", "description"));
        items.add(new RowItem("4th item", "here"));
        items.add(new RowItem("5th item", "or something, really long side description, what happens here?"));
        items.add(new RowItem("6th item", "another"));
        items.add(new RowItem("7th item", "and"));
        items.add(new RowItem("8th item", "another"));
        view.setRowItems(items);
    }

    public static void mock2Items(ExpandableCollectionRow view) {
        view.setBottomText((CharSequence) "disclaimer text. AKA: Can't blame us when things go wrong text long something words more words yep.");
        view.setExpandText((CharSequence) "Click to expand me");
        List<RowItem> items = new ArrayList<>();
        items.add(new RowItem("1st item", "put"));
        items.add(new RowItem("2nd item", "a"));
        view.setRowItems(items);
    }

    public static void mock1Item(ExpandableCollectionRow view) {
        view.setBottomText((CharSequence) "disclaimer text. AKA: Can't blame us when things go wrong text long something words more words yep.");
        view.setExpandText((CharSequence) "Click to expand me");
        List<RowItem> items = new ArrayList<>();
        items.add(new RowItem("1st item", "put"));
        view.setRowItems(items, false);
    }
}

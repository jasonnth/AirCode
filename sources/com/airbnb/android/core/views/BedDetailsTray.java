package com.airbnb.android.core.views;

import android.content.Context;
import android.support.p002v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.viewcomponents.models.BedDetailsCardEpoxyModel_;
import com.airbnb.p027n2.components.BedDetailsCard;
import com.airbnb.p027n2.components.MicroSectionHeader;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

public class BedDetailsTray extends LinearLayout implements DividerView {
    private List<ListingRoom> cachedRooms;
    @BindView
    LinearLayoutCompat cardsContainer;
    @BindView
    View divider;
    @BindView
    MicroSectionHeader sectionHeader;

    public BedDetailsTray(Context context) {
        super(context);
        init();
    }

    public BedDetailsTray(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BedDetailsTray(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C0716R.layout.bed_details_tray, this);
        ButterKnife.bind((View) this);
    }

    public void setup(CharSequence title, List<ListingRoom> rooms) {
        this.sectionHeader.setTitle(title);
        if (!rooms.equals(this.cachedRooms)) {
            this.cardsContainer.removeAllViews();
            boolean firstChild = true;
            for (ListingRoom room : rooms) {
                BedDetailsCard card = new BedDetailsCard(getContext());
                new BedDetailsCardEpoxyModel_().room(room).bind(card);
                if (!firstChild) {
                    card.setPadding(getResources().getDimensionPixelSize(C0716R.dimen.n2_bed_details_tray_divider_spacing), 0, 0, 0);
                }
                this.cardsContainer.addView(card, new LayoutParams(-2, -1));
                firstChild = false;
            }
            this.cachedRooms = rooms;
        }
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }
}

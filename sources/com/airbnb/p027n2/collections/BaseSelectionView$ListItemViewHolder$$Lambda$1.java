package com.airbnb.p027n2.collections;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.collections.BaseSelectionView$ListItemViewHolder$$Lambda$1 */
final /* synthetic */ class BaseSelectionView$ListItemViewHolder$$Lambda$1 implements OnClickListener {
    private final ListItemViewHolder arg$1;
    private final int arg$2;

    private BaseSelectionView$ListItemViewHolder$$Lambda$1(ListItemViewHolder listItemViewHolder, int i) {
        this.arg$1 = listItemViewHolder;
        this.arg$2 = i;
    }

    public static OnClickListener lambdaFactory$(ListItemViewHolder listItemViewHolder, int i) {
        return new BaseSelectionView$ListItemViewHolder$$Lambda$1(listItemViewHolder, i);
    }

    public void onClick(View view) {
        ListItemViewHolder.lambda$bind$0(this.arg$1, this.arg$2, view);
    }
}

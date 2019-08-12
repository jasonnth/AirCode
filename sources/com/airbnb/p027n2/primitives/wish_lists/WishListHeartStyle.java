package com.airbnb.p027n2.primitives.wish_lists;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.airbnb.n2.R;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.primitives.wish_lists.WishListHeartStyle */
public enum WishListHeartStyle {
    SMALL(R.dimen.n2_wish_list_heart_size_small, R.dimen.n2_wish_list_heart_padding_small),
    MEDIUM(R.dimen.n2_wish_list_heart_size_medium, R.dimen.n2_wish_list_heart_padding_medium);
    
    private final int paddingRes;
    private final int sizeRes;

    private WishListHeartStyle(int sizeRes2, int paddingRes2) {
        this.sizeRes = sizeRes2;
        this.paddingRes = paddingRes2;
    }

    public void updateView(View view) {
        int sizePx = view.getResources().getDimensionPixelSize(this.sizeRes);
        int paddingPx = view.getResources().getDimensionPixelSize(this.paddingRes);
        LayoutParams lp = view.getLayoutParams();
        ViewLibUtils.setPadding(view, paddingPx);
        lp.width = sizePx;
        lp.height = sizePx;
        view.setLayoutParams(lp);
    }
}

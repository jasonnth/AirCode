package com.airbnb.android.core.views;

import com.airbnb.android.core.views.OptionsMenuFactory.Listener;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetItemClickListener;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetMenuItem;
import java.util.List;

final /* synthetic */ class OptionsMenuFactory$$Lambda$4 implements BottomSheetItemClickListener {
    private final List arg$1;
    private final Listener arg$2;

    private OptionsMenuFactory$$Lambda$4(List list, Listener listener) {
        this.arg$1 = list;
        this.arg$2 = listener;
    }

    public static BottomSheetItemClickListener lambdaFactory$(List list, Listener listener) {
        return new OptionsMenuFactory$$Lambda$4(list, listener);
    }

    public void onBottomSheetItemClick(BottomSheetMenuItem bottomSheetMenuItem) {
        this.arg$2.itemSelected(((Item) this.arg$1.get(bottomSheetMenuItem.getId())).value);
    }
}

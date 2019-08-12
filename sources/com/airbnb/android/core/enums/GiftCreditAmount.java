package com.airbnb.android.core.enums;

import com.airbnb.android.core.C0716R;
import com.google.common.collect.ImmutableList;
import java.util.List;

public enum GiftCreditAmount {
    GiftCreditAmount1(C0716R.C0718id.gc_amount_1_cell, 25),
    GiftCreditAmount2(C0716R.C0718id.gc_amount_2_cell, 50),
    GiftCreditAmount3(C0716R.C0718id.gc_amount_3_cell, 100),
    GiftCreditAmount4(C0716R.C0718id.gc_amount_4_cell, 500);
    
    public static final List<GiftCreditAmount> giftCreditAmounts = null;
    private final int giftCreditAmount;
    private final int viewId;

    static {
        giftCreditAmounts = ImmutableList.m1288of(GiftCreditAmount1, GiftCreditAmount2, GiftCreditAmount3, GiftCreditAmount4);
    }

    private GiftCreditAmount(int viewId2, int giftCreditAmount2) {
        this.viewId = viewId2;
        this.giftCreditAmount = giftCreditAmount2;
    }

    public int getViewId() {
        return this.viewId;
    }

    public int getAmount() {
        return this.giftCreditAmount;
    }

    public static GiftCreditAmount getGiftCreditAmountForResourceId(int viewId2) {
        for (GiftCreditAmount giftCreditAmount2 : giftCreditAmounts) {
            if (giftCreditAmount2.getViewId() == viewId2) {
                return giftCreditAmount2;
            }
        }
        throw new IllegalStateException("Invalid view id in GiftCreditAmount:getGiftCreditAmountForResourceId");
    }
}

package com.airbnb.p027n2.epoxy;

/* renamed from: com.airbnb.n2.epoxy.AirModel */
public interface AirModel {
    int getDividerViewType();

    int getSpanSize(int i, int i2, int i3);

    Boolean isShowingDivider();

    AirModel showDivider(boolean z);

    boolean supportsDividers();
}

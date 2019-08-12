package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ExpandableCollectionRow;

/* renamed from: com.airbnb.n2.ExpandableCollectionRowMockAdapter */
public final class ExpandableCollectionRowMockAdapter implements DLSMockAdapter<ExpandableCollectionRow> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            case 2:
                return "";
            default:
                return "";
        }
    }

    public DLSStyleWrapperImpl getStyle(int position) {
        switch (position) {
            case 0:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 1:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 2:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(ExpandableCollectionRow view, int position) {
        switch (position) {
            case 0:
                ExpandableCollectionRow.mockMoreThan2Items(view);
                return;
            case 1:
                ExpandableCollectionRow.mock2Items(view);
                return;
            case 2:
                ExpandableCollectionRow.mock1Item(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}

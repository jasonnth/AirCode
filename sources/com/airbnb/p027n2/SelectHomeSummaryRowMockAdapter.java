package com.airbnb.p027n2;

import com.airbnb.p027n2.components.decide.select.SelectHomeSummaryRow;

/* renamed from: com.airbnb.n2.SelectHomeSummaryRowMockAdapter */
public final class SelectHomeSummaryRowMockAdapter implements DLSMockAdapter<SelectHomeSummaryRow> {
    public int getItemCount() {
        return 1;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            default:
                return "";
        }
    }

    public DLSStyleWrapperImpl getStyle(int position) {
        switch (position) {
            case 0:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            default:
                return null;
        }
    }

    public void bindView(SelectHomeSummaryRow view, int position) {
        switch (position) {
            case 0:
                SelectHomeSummaryRow.mock(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}

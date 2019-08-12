package com.airbnb.p027n2;

import com.airbnb.p027n2.components.TextRow;

/* renamed from: com.airbnb.n2.TextRowMockAdapter */
public final class TextRowMockAdapter implements DLSMockAdapter<TextRow> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "No max lines";
            case 2:
                return "With rich text";
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

    public void bindView(TextRow view, int position) {
        switch (position) {
            case 0:
                TextRow.mockCollapsed(view);
                return;
            case 1:
                TextRow.mockExpanded(view);
                return;
            case 2:
                TextRow.mockRichText(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}

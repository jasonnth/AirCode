package com.airbnb.p027n2;

import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;

/* renamed from: com.airbnb.n2.FixedActionFooterMockAdapter */
public final class FixedActionFooterMockAdapter implements DLSMockAdapter<FixedActionFooter> {
    public int getItemCount() {
        return 10;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Rausch Waiting";
            case 1:
                return "Rausch";
            case 2:
                return "";
            case 3:
                return "Rausch Disabled";
            case 4:
                return "Babu Waiting";
            case 5:
                return "Babu";
            case 6:
                return "Babu Disabled";
            case 7:
                return "White Waiting";
            case 8:
                return "White";
            case 9:
                return "White Disabled";
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
            case 3:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 4:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 5:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 6:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 7:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 8:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 9:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(FixedActionFooter view, int position) {
        switch (position) {
            case 0:
                FixedActionFooter.mockRauschWaiting(view);
                return;
            case 1:
                FixedActionFooter.mockRausch(view);
                return;
            case 2:
                FixedActionFooter.mockRauschLongButtonText(view);
                return;
            case 3:
                FixedActionFooter.mockRauschDisabled(view);
                return;
            case 4:
                FixedActionFooter.mockBabuWaiting(view);
                return;
            case 5:
                FixedActionFooter.mockBabu(view);
                return;
            case 6:
                FixedActionFooter.mockBabuDisabled(view);
                return;
            case 7:
                FixedActionFooter.mockJellyfishWaiting(view);
                return;
            case 8:
                FixedActionFooter.mockJellyfish(view);
                return;
            case 9:
                FixedActionFooter.mockJellyfishDisabled(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 1;
    }
}

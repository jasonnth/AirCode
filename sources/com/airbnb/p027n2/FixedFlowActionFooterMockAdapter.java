package com.airbnb.p027n2;

import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionFooter;

/* renamed from: com.airbnb.n2.FixedFlowActionFooterMockAdapter */
public final class FixedFlowActionFooterMockAdapter implements DLSMockAdapter<FixedFlowActionFooter> {
    public int getItemCount() {
        return 13;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Rausch";
            case 1:
                return "";
            case 2:
                return "";
            case 3:
                return "Rausch No Subtitle";
            case 4:
                return "Rausch No Titles";
            case 5:
                return "Rausch Disabled";
            case 6:
                return "Rausch Waiting";
            case 7:
                return "Babu";
            case 8:
                return "Babu Disabled";
            case 9:
                return "Babu Waiting";
            case 10:
                return "White";
            case 11:
                return "White Disabled";
            case 12:
                return "White Waiting";
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
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 8:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 9:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 10:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 11:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 12:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(FixedFlowActionFooter view, int position) {
        switch (position) {
            case 0:
                FixedFlowActionFooter.mockRausch(view);
                return;
            case 1:
                FixedFlowActionFooter.mockRauschLongTitles(view);
                return;
            case 2:
                FixedFlowActionFooter.mockRauschLongButtonText(view);
                return;
            case 3:
                FixedFlowActionFooter.mockRauschNoSubtitle(view);
                return;
            case 4:
                FixedFlowActionFooter.mockRauschNoTitles(view);
                return;
            case 5:
                FixedFlowActionFooter.mockRauschDisabled(view);
                return;
            case 6:
                FixedFlowActionFooter.mockRauschWaiting(view);
                return;
            case 7:
                FixedFlowActionFooter.mockBabu(view);
                return;
            case 8:
                FixedFlowActionFooter.mockBabuDisabled(view);
                return;
            case 9:
                FixedFlowActionFooter.mockBabuWaiting(view);
                return;
            case 10:
                FixedFlowActionFooter.mockJellyfish(view);
                return;
            case 11:
                FixedFlowActionFooter.mockJellyfishDisabled(view);
                return;
            case 12:
                FixedFlowActionFooter.mockJellyfishWaiting(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}

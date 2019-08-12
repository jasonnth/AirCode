package com.airbnb.p027n2;

import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooter;

/* renamed from: com.airbnb.n2.FixedFlowActionAdvanceFooterMockAdapter */
public final class FixedFlowActionAdvanceFooterMockAdapter implements DLSMockAdapter<FixedFlowActionAdvanceFooter> {
    public int getItemCount() {
        return 16;
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
                return "Rausch Collapsed";
            case 8:
                return "Babu";
            case 9:
                return "Babu Disabled";
            case 10:
                return "Babu Waiting";
            case 11:
                return "Babu Collapsed";
            case 12:
                return "White";
            case 13:
                return "White Disabled";
            case 14:
                return "White Waiting";
            case 15:
                return "White Collapsed";
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
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 11:
                return DLSStyleWrapperImpl.from(DLSStyle.White);
            case 12:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 13:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 14:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 15:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(FixedFlowActionAdvanceFooter view, int position) {
        switch (position) {
            case 0:
                FixedFlowActionAdvanceFooter.mockRausch(view);
                return;
            case 1:
                FixedFlowActionAdvanceFooter.mockRauschLongTitles(view);
                return;
            case 2:
                FixedFlowActionAdvanceFooter.mockRauschLongButtonText(view);
                return;
            case 3:
                FixedFlowActionAdvanceFooter.mockRauschNoSubtitle(view);
                return;
            case 4:
                FixedFlowActionAdvanceFooter.mockRauschNoTitles(view);
                return;
            case 5:
                FixedFlowActionAdvanceFooter.mockRauschDisabled(view);
                return;
            case 6:
                FixedFlowActionAdvanceFooter.mockRauschWaiting(view);
                return;
            case 7:
                FixedFlowActionAdvanceFooter.mockRauschCollapsed(view);
                return;
            case 8:
                FixedFlowActionAdvanceFooter.mockBabu(view);
                return;
            case 9:
                FixedFlowActionAdvanceFooter.mockBabuDisabled(view);
                return;
            case 10:
                FixedFlowActionAdvanceFooter.mockBabuWaiting(view);
                return;
            case 11:
                FixedFlowActionAdvanceFooter.mockBabuCollapsed(view);
                return;
            case 12:
                FixedFlowActionAdvanceFooter.mockJellyfish(view);
                return;
            case 13:
                FixedFlowActionAdvanceFooter.mockJellyfishDisabled(view);
                return;
            case 14:
                FixedFlowActionAdvanceFooter.mockJellyfishWaiting(view);
                return;
            case 15:
                FixedFlowActionAdvanceFooter.mockJellyfishCollapsed(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}

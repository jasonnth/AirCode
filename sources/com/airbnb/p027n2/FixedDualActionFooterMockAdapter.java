package com.airbnb.p027n2;

import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;

/* renamed from: com.airbnb.n2.FixedDualActionFooterMockAdapter */
public final class FixedDualActionFooterMockAdapter implements DLSMockAdapter<FixedDualActionFooter> {
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
                return "Rausch No Secondary Action";
            case 4:
                return "Rausch No Primary Action";
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

    public void bindView(FixedDualActionFooter view, int position) {
        switch (position) {
            case 0:
                FixedDualActionFooter.mockRausch(view);
                return;
            case 1:
                FixedDualActionFooter.mockRauschLongPrimaryButtonText(view);
                return;
            case 2:
                FixedDualActionFooter.mockRauschLongSecondaryButtonText(view);
                return;
            case 3:
                FixedDualActionFooter.mockRauschNoSecondaryAction(view);
                return;
            case 4:
                FixedDualActionFooter.mockRauschNoPrimaryAction(view);
                return;
            case 5:
                FixedDualActionFooter.mockRauschDisabled(view);
                return;
            case 6:
                FixedDualActionFooter.mockRauschWaiting(view);
                return;
            case 7:
                FixedDualActionFooter.mockBabu(view);
                return;
            case 8:
                FixedDualActionFooter.mockBabuDisabled(view);
                return;
            case 9:
                FixedDualActionFooter.mockBabuWaiting(view);
                return;
            case 10:
                FixedDualActionFooter.mockJellyfish(view);
                return;
            case 11:
                FixedDualActionFooter.mockJellyfishDisabled(view);
                return;
            case 12:
                FixedDualActionFooter.mockJellyfishWaiting(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}

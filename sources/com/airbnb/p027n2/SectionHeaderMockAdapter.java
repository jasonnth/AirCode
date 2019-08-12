package com.airbnb.p027n2;

import com.airbnb.p027n2.components.SectionHeader;

/* renamed from: com.airbnb.n2.SectionHeaderMockAdapter */
public final class SectionHeaderMockAdapter implements DLSMockAdapter<SectionHeader> {
    public int getItemCount() {
        return 6;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            case 2:
                return "";
            case 3:
                return "With Babu Button";
            case 4:
                return "Secondary";
            case 5:
                return "Secondary +";
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
            default:
                return null;
        }
    }

    public void bindView(SectionHeader view, int position) {
        switch (position) {
            case 0:
                SectionHeader.mock(view);
                return;
            case 1:
                SectionHeader.mockPlus(view);
                return;
            case 2:
                SectionHeader.mockPlusLongTitle(view);
                return;
            case 3:
                SectionHeader.mockBabuButton(view);
                return;
            case 4:
                SectionHeader.mockSecondary(view);
                return;
            case 5:
                SectionHeader.mockSecondaryPlus(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 1;
    }
}

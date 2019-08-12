package com.airbnb.p027n2;

import com.airbnb.p027n2.components.StandardRow;

/* renamed from: com.airbnb.n2.StandardRowMockAdapter */
public final class StandardRowMockAdapter implements DLSMockAdapter<StandardRow> {
    public int getItemCount() {
        return 8;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "BasicRow";
            case 1:
                return "BasicRow + Subtitle";
            case 2:
                return "BasicRow + Icon";
            case 3:
                return "BasicRow + Icon + Badge";
            case 4:
                return "InfoRow";
            case 5:
                return "InfoRow + Subtitle";
            case 6:
                return "InfoActionRow";
            case 7:
                return "InfoActionRow + Subtitle";
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
            default:
                return null;
        }
    }

    public void bindView(StandardRow view, int position) {
        switch (position) {
            case 0:
                StandardRow.mockBasic(view);
                return;
            case 1:
                StandardRow.mockBasicPlus(view);
                return;
            case 2:
                StandardRow.mockBasicPlusIcon(view);
                return;
            case 3:
                StandardRow.mockBasicPlusIconWithBadge(view);
                return;
            case 4:
                StandardRow.mockInfo(view);
                return;
            case 5:
                StandardRow.mockInfoPlus(view);
                return;
            case 6:
                StandardRow.mockAction(view);
                return;
            case 7:
                StandardRow.mockActionPlus(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 5;
    }
}

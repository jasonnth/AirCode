package com.airbnb.p027n2;

import com.airbnb.p027n2.components.StandardRowWithLabel;

/* renamed from: com.airbnb.n2.StandardRowWithLabelMockAdapter */
public final class StandardRowWithLabelMockAdapter implements DLSMockAdapter<StandardRowWithLabel> {
    public int getItemCount() {
        return 10;
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
            case 8:
                return "LabelRow";
            case 9:
                return "LabelRow Babu";
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
            default:
                return null;
        }
    }

    public void bindView(StandardRowWithLabel view, int position) {
        switch (position) {
            case 0:
                StandardRowWithLabel.mockBasic(view);
                return;
            case 1:
                StandardRowWithLabel.mockBasicPlus(view);
                return;
            case 2:
                StandardRowWithLabel.mockBasicPlusIcon(view);
                return;
            case 3:
                StandardRowWithLabel.mockBasicPlusIconWithBadge(view);
                return;
            case 4:
                StandardRowWithLabel.mockInfo(view);
                return;
            case 5:
                StandardRowWithLabel.mockInfoPlus(view);
                return;
            case 6:
                StandardRowWithLabel.mockAction(view);
                return;
            case 7:
                StandardRowWithLabel.mockActionPlus(view);
                return;
            case 8:
                StandardRowWithLabel.mockLabel(view);
                return;
            case 9:
                StandardRowWithLabel.mockLabelBabu(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 8;
    }
}

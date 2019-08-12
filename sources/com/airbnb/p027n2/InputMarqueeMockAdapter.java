package com.airbnb.p027n2;

import com.airbnb.p027n2.components.InputMarquee;

/* renamed from: com.airbnb.n2.InputMarqueeMockAdapter */
public final class InputMarqueeMockAdapter implements DLSMockAdapter<InputMarquee> {
    public int getItemCount() {
        return 3;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "InputMarquee";
            case 1:
                return "InputMarquee + Inputted Text";
            case 2:
                return "InputMarquee Inverse";
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
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(InputMarquee view, int position) {
        switch (position) {
            case 0:
                InputMarquee.mock(view);
                return;
            case 1:
                InputMarquee.mockPlusInputtedText(view);
                return;
            case 2:
                InputMarquee.mockInverse(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}

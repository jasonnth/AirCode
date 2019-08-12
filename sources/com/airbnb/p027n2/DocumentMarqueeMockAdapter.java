package com.airbnb.p027n2;

import com.airbnb.p027n2.components.DocumentMarquee;

/* renamed from: com.airbnb.n2.DocumentMarqueeMockAdapter */
public final class DocumentMarqueeMockAdapter implements DLSMockAdapter<DocumentMarquee> {
    public int getItemCount() {
        return 5;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "Title only";
            case 1:
                return "Long title";
            case 2:
                return "Title + Caption";
            case 3:
                return "Title + Link";
            case 4:
                return "Title + Rich caption";
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
            default:
                return null;
        }
    }

    public void bindView(DocumentMarquee view, int position) {
        switch (position) {
            case 0:
                DocumentMarquee.mock(view);
                return;
            case 1:
                DocumentMarquee.mockLongTitle(view);
                return;
            case 2:
                DocumentMarquee.mockPlusCaption(view);
                return;
            case 3:
                DocumentMarquee.mockPlusLink(view);
                return;
            case 4:
                DocumentMarquee.mockRickCaption(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 2;
    }
}

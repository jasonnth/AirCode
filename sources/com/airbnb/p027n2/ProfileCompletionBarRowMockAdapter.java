package com.airbnb.p027n2;

import com.airbnb.p027n2.components.ProfileCompletionBarRow;

/* renamed from: com.airbnb.n2.ProfileCompletionBarRowMockAdapter */
public final class ProfileCompletionBarRowMockAdapter implements DLSMockAdapter<ProfileCompletionBarRow> {
    public int getItemCount() {
        return 6;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "ProfileCompletionBarRow [Above Standard]";
            case 1:
                return "ProfileCompletionBarRow [Standard]";
            case 2:
                return "ProfileCompletionBarRow [Below Standard]";
            case 3:
                return "ProfileCompletionBarRow + Subtitle [Above Standard]";
            case 4:
                return "ProfileCompletionBarRow + Subtitle [Standard]";
            case 5:
                return "ProfileCompletionBarRow + Subtitle [Below Standard]";
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

    public void bindView(ProfileCompletionBarRow view, int position) {
        switch (position) {
            case 0:
                ProfileCompletionBarRow.mockAboveStandard(view);
                return;
            case 1:
                ProfileCompletionBarRow.mockStandard(view);
                return;
            case 2:
                ProfileCompletionBarRow.mockBelowStandard(view);
                return;
            case 3:
                ProfileCompletionBarRow.mockSubtitleAboveStandard(view);
                return;
            case 4:
                ProfileCompletionBarRow.mockSubtitleStandard(view);
                return;
            case 5:
                ProfileCompletionBarRow.mockSubtitleBelowStandard(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}

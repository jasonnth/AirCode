package com.airbnb.p027n2;

import com.airbnb.p027n2.components.InputSuggestionActionRow;

/* renamed from: com.airbnb.n2.InputSuggestionActionRowMockAdapter */
public final class InputSuggestionActionRowMockAdapter implements DLSMockAdapter<InputSuggestionActionRow> {
    public int getItemCount() {
        return 7;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "With subtitle";
            case 2:
                return "With subtitle and label";
            case 3:
                return "";
            case 4:
                return "";
            case 5:
                return "With subtitle";
            case 6:
                return "With subtitle and label";
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
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 5:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 6:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(InputSuggestionActionRow view, int position) {
        switch (position) {
            case 0:
                InputSuggestionActionRow.mock(view);
                return;
            case 1:
                InputSuggestionActionRow.mockSubtitle(view);
                return;
            case 2:
                InputSuggestionActionRow.mockSubtitleLabel(view);
                return;
            case 3:
                InputSuggestionActionRow.mockLongTitleAndSubtitle(view);
                return;
            case 4:
                InputSuggestionActionRow.mockInverse(view);
                return;
            case 5:
                InputSuggestionActionRow.mockSubtitleInverse(view);
                return;
            case 6:
                InputSuggestionActionRow.mockSubtitleLabelInverse(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 2;
    }
}

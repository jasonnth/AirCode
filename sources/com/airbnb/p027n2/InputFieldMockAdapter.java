package com.airbnb.p027n2;

import com.airbnb.p027n2.components.InputField;

/* renamed from: com.airbnb.n2.InputFieldMockAdapter */
public final class InputFieldMockAdapter implements DLSMockAdapter<InputField> {
    public int getItemCount() {
        return 5;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "With inputted text";
            case 2:
                return "With inputted text that wraps";
            case 3:
                return "Error";
            case 4:
                return "Subtitle";
            default:
                return "";
        }
    }

    public DLSStyleWrapperImpl getStyle(int position) {
        switch (position) {
            case 0:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 1:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 2:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 3:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            case 4:
                return DLSStyleWrapperImpl.from(DLSStyle.Sheet);
            default:
                return null;
        }
    }

    public void bindView(InputField view, int position) {
        switch (position) {
            case 0:
                InputField.mock(view);
                return;
            case 1:
                InputField.mockInputtedText(view);
                return;
            case 2:
                InputField.mockInputtedTextWraps(view);
                return;
            case 3:
                InputField.mockError(view);
                return;
            case 4:
                InputField.mockSubtitle(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}

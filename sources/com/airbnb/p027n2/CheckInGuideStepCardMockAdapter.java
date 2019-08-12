package com.airbnb.p027n2;

import com.airbnb.p027n2.components.CheckInGuideStepCard;

/* renamed from: com.airbnb.n2.CheckInGuideStepCardMockAdapter */
public final class CheckInGuideStepCardMockAdapter implements DLSMockAdapter<CheckInGuideStepCard> {
    public int getItemCount() {
        return 9;
    }

    public String getName(int position) {
        switch (position) {
            case 0:
                return "CheckInGuideStepCard";
            case 1:
                return "CheckInGuideStepCard - empty - step 1";
            case 2:
                return "CheckInGuideStepCard + light photo";
            case 3:
                return "CheckInGuideStepCard + colored text";
            case 4:
                return "CheckInGuideStepCard + partially colored text";
            case 5:
                return "CheckInGuideStepCard + very long note";
            case 6:
                return "CheckInGuideStepCard + loading";
            case 7:
                return "CheckInGuideStepCard + loading + note";
            case 8:
                return "CheckInGuideStepCard + error + note";
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
            default:
                return null;
        }
    }

    public void bindView(CheckInGuideStepCard view, int position) {
        switch (position) {
            case 0:
                CheckInGuideStepCard.mockFullStep(view);
                return;
            case 1:
                CheckInGuideStepCard.mockEmptyFirstStep(view);
                return;
            case 2:
                CheckInGuideStepCard.mockNoPhoto(view);
                return;
            case 3:
                CheckInGuideStepCard.mockColoredText(view);
                return;
            case 4:
                CheckInGuideStepCard.mockPartiallyColoredText(view);
                return;
            case 5:
                CheckInGuideStepCard.mockLongNote(view);
                return;
            case 6:
                CheckInGuideStepCard.mockLoadingPhotoWithInstructions(view);
                return;
            case 7:
                CheckInGuideStepCard.mockLoadingPhotoWithNote(view);
                return;
            case 8:
                CheckInGuideStepCard.mockErrorPhotoWithNote(view);
                return;
            default:
                return;
        }
    }

    public int getDefaultPosition() {
        return 0;
    }
}

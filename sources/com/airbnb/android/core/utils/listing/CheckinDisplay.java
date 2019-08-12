package com.airbnb.android.core.utils.listing;

import android.text.TextUtils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.CheckInInformation;

public class CheckinDisplay {
    public static String getSelectTypeInformationalString(CheckInInformation checkinType) {
        if (!TextUtils.isEmpty(checkinType.getInstruction())) {
            return checkinType.getInstruction();
        }
        return checkinType.getLocalizedDescription();
    }

    public static String getCheckinTypeEditPageTitle(CheckInInformation checkinType) {
        return checkinType.getAmenity().getName();
    }

    public static String getCheckinTypeEditPageInstructions(CheckInInformation checkInType) {
        return checkInType.getLocalizedInstructionSubtitle();
    }

    public static String getCheckinTypeEditPageHintText(CheckInInformation checkinType) {
        return checkinType.getLocalizedInstructionHint();
    }

    public static int getCheckInEditNotePageTitleRes(String note) {
        return TextUtils.isEmpty(note) ? C0716R.string.manage_listing_check_in_guide_add_note_title : C0716R.string.manage_listing_check_in_guide_edit_note_title;
    }

    public static int getCheckInStepInstructions(int stepNum) {
        return stepNum == 0 ? C0716R.string.manage_listing_check_in_guide_add_first_step_instructions : C0716R.string.manage_listing_check_in_guide_add_next_step_instructions;
    }
}
